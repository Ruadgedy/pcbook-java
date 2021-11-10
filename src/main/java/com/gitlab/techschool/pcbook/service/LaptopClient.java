package com.gitlab.techschool.pcbook.service;

import com.gitlab.techschool.pcbook.pb.*;
import com.gitlab.techschool.pcbook.sample.Generator;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author yuhao
 * @date: 2021/11/6
 * @description:
 */
public class LaptopClient {
	private static final Logger logger = Logger.getLogger(LaptopClient.class.getName());

	private final ManagedChannel channel;
	// 阻塞式stub
	private final LaptopServiceGrpc.LaptopServiceBlockingStub blockingStub;
	// 不能使用BlockingStub去调用streaming RPC
	private final LaptopServiceGrpc.LaptopServiceStub asyncStub;

	public LaptopClient(String host, int port){
		channel = ManagedChannelBuilder.forAddress(host,port)
				.usePlaintext() // 使用明文传输，不用ssl
				.build();

		blockingStub = LaptopServiceGrpc.newBlockingStub(channel);
		asyncStub = LaptopServiceGrpc.newStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public void createLaptop(Laptop laptop){
		CreateLaptopRequest request = CreateLaptopRequest.newBuilder().setLaptop(laptop).build();
		CreateLaptopResponse response = CreateLaptopResponse.getDefaultInstance();

		try {
			// 模拟请求超时(带有五秒的请求超时限制)
			response = blockingStub.withDeadlineAfter(5,TimeUnit.SECONDS).createLaptop(request);
//			response = blockingStub.createLaptop(request);
		}catch (StatusRuntimeException e){
			if (e.getStatus().getCode() == Status.Code.ALREADY_EXISTS){
				logger.info("laptop ID already exists");
				return;
			}
			logger.log(Level.SEVERE,"request failed:" + e.getMessage());
			return;
		} catch (Exception e){
			logger.log(Level.SEVERE,"request failed : " + e.getMessage());
			return;
		}

		logger.info("laptop created with ID:" + response.getId());
	}

	// 上传图片
	public void uploadImages(String laptopID, String imagePath) throws InterruptedException {
		// 因为上传图片使用的是异步stub，所以上传任务会交给一个单独的线程去异步执行，为了在主线程拿到结果，我们在下面定义了一个同步器
		final CountDownLatch finalLatch = new CountDownLatch(1);

		// The output of this call will be another StreamObserver of UploadImageRequest
		StreamObserver<UploadImageRequest> requestObserver = asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
				.uploadImage(new StreamObserver<UploadImageResponse>() {    // 拿到server端的响应
					@Override
					public void onNext(UploadImageResponse response) {
						logger.info("receive response:\n" + response);
					}

					@Override
					public void onError(Throwable t) {
						logger.log(Level.SEVERE, "upload failed:" + t.getMessage());
						finalLatch.countDown();
					}

					@Override
					public void onCompleted() {
						logger.info("image uploaded");
						finalLatch.countDown();
					}
				});


		FileInputStream fis;
		try {
			fis = new FileInputStream(imagePath);
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE,"cannot read image file: " + e.getMessage());
			return;
		}

		String imageType = imagePath.substring(imagePath.lastIndexOf("."));
		ImageInfo imageInfo = ImageInfo.newBuilder().setImageType(imageType).setLaptopId(laptopID).build();
		UploadImageRequest request = UploadImageRequest.newBuilder().setInfo(imageInfo).build();

		try {
			requestObserver.onNext(request);
			logger.info("sent image info:\n" + imageInfo);

			byte[] buffer = new byte[1024];
			while (true) {
				// read 返回-1，代表读取到了末尾
				int n = fis.read(buffer);
				if (n <= 0){
					break;
				}

				// 如果finishLatch为0，说明上面的onError被触发了（出现了预期外的错误）
				if (finalLatch.getCount() == 0){
					return;
				}

				// 构建新的请求。持续的将图片数据发给server
				request = UploadImageRequest.newBuilder()
						.setChunkData(ByteString.copyFrom(buffer, 0, n))
						.build();
				requestObserver.onNext(request);
				logger.info("sent image chunk with size: " + n);
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE,"unexpected error:" + e.getMessage());
			requestObserver.onError(e);
			return;
		}

		// 图片传输完后后，需要告诉server传输完成
		requestObserver.onCompleted();

		if (!finalLatch.await(1,TimeUnit.MINUTES)) {
			logger.warning("request cannot finish within 1 minute");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		LaptopClient client = new LaptopClient("0.0.0.0", 8080);

		Generator generator = new Generator();

		try {
			// TODO: test create and search laptops
//			for (int i = 0; i < 10; i++) {
//				Laptop laptop = generator.NewLaptop();
//				client.createLaptop(laptop);
//			}
//
//			Memory minRam = Memory.newBuilder()
//					.setValue(8)
//					.setUnit(Memory.Unit.GIGABYTE)
//					.build();
//
//			Filter filter = Filter.newBuilder()
//					.setMaxPriceUsd(3000)
//					.setMinCpuCores(4)
//					.setMinCpuGhz(2.5)
//					.setMinRam(minRam)
//					.build();
//
//			client.searchLaptop(filter);

			// TODO: Test upload laptop image
			Laptop laptop = generator.NewLaptop();
			client.createLaptop(laptop);    // 新建laptop
			client.uploadImages(laptop.getId(),"img/laptop.png");   // 上传图片
		}finally {
			client.shutdown();
		}
	}

	private void searchLaptop(Filter filter) {
		logger.info("search started");

		SearchLaptopRequest request = SearchLaptopRequest.newBuilder().setFilter(filter).build();
		Iterator<SearchLaptopResponse> responseIterator = blockingStub
				.withDeadlineAfter(5,TimeUnit.SECONDS)
				.searchLaptop(request);

		while (responseIterator.hasNext()){
			SearchLaptopResponse response = responseIterator.next();
			Laptop laptop = response.getLaptop();
			logger.info("- found: " + laptop.getId());
		}
		logger.info("search complete");
	}
}
