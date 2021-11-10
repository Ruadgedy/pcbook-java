package com.gitlab.techschool.pcbook.service;


import com.gitlab.techschool.pcbook.pb.*;
import com.google.protobuf.ByteString;
import io.grpc.Context;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @author yuhao
 * @date: 2021/11/6
 * @description:
 */
public class LaptopService extends LaptopServiceGrpc.LaptopServiceImplBase {

	private static final Logger logger = Logger.getLogger(LaptopService.class.getName());

	private LaptopStore laptopStore;
	private ImageStore imageStore;

	public LaptopService(LaptopStore laptopStore, ImageStore imageStore) {
		this.laptopStore = laptopStore;
		this.imageStore = imageStore;
	}

	@Override
	public void createLaptop(CreateLaptopRequest request, StreamObserver<CreateLaptopResponse> responseObserver) {
		Laptop laptop = request.getLaptop();

		String id = laptop.getId();
		logger.info("got a create-laptop request with ID: " + id);

		UUID uuid;
		if (id.isEmpty()){
			uuid = UUID.randomUUID();
		} else {
			try {
				uuid = UUID.fromString(id);
			}catch (IllegalArgumentException e){
				responseObserver.onError(
						Status.INVALID_ARGUMENT
							.withDescription(e.getMessage())
							.asRuntimeException()
				);
				return;
			}
		}

		// heavy processing
//		try {
//			TimeUnit.SECONDS.sleep(6);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		// 判断请求是否被取消
		if (Context.current().isCancelled()){
			logger.info("request is cancelled");
			responseObserver.onError(
					Status.CANCELLED
							.withDescription("request is cancelled")
							.asRuntimeException()
			);
			return;
		}

		Laptop other = laptop.toBuilder().setId(uuid.toString()).build();
		// Save other laptop tp the in-memory store
		try {
			laptopStore.Save(other);
		} catch (AlreadyExistsException e) {
			responseObserver.onError(
					Status.ALREADY_EXISTS
						.withDescription(e.getMessage())
						.asRuntimeException()
			);
			return;
		} catch (Exception e){
			responseObserver.onError(
					Status.INTERNAL
						.withDescription(e.getMessage())
						.asRuntimeException()
			);
			return;
		}

		// 创建响应
		CreateLaptopResponse response = CreateLaptopResponse.newBuilder().setId(other.getId()).build();
		// 向client传递响应
		responseObserver.onNext(response);
		// 告诉客户端，rpc通信结束
		responseObserver.onCompleted();

		logger.info("saved laptop with ID: " + other.getId());
	}

	// gRPC Stream 输出
	@Override
	public void searchLaptop(SearchLaptopRequest request, StreamObserver<SearchLaptopResponse> responseObserver) {
		Filter filter = request.getFilter();
		logger.info("got a search-laptop request with filter:\n" + filter);

		laptopStore.Search(Context.current(), filter, laptop -> {
			logger.info("found laptop with ID: " + laptop.getId());
			SearchLaptopResponse response = SearchLaptopResponse.newBuilder().setLaptop(laptop).build();
			responseObserver.onNext(response);
		});

		responseObserver.onCompleted();
		logger.info("search laptop complete");
	}

	// gRPC stream输入 有着极大的不同，会发现输入成为了返回值
	@Override
	public StreamObserver<UploadImageRequest> uploadImage(StreamObserver<UploadImageResponse> responseObserver) {
		return new StreamObserver<UploadImageRequest>() {
			// 定义最大图片大小
			private static final int maxImageSize = 1 << 23; // 1Mbit
			private String laptopID;
			private String imageType;
			private ByteArrayOutputStream imageData;

			@Override
			public void onNext(UploadImageRequest request) {
				// 上传图片的首个请求是ImageInfo字段
				if (request.getDataCase() == UploadImageRequest.DataCase.INFO) {
					ImageInfo info = request.getInfo();
					logger.info("receive image info:\n" + info);

					laptopID = info.getLaptopId();
					imageType = info.getImageType();
					// 初始化
					imageData = new ByteArrayOutputStream();

					// check laptop exists
					Laptop found = laptopStore.Find(laptopID);
					if (found == null){
						responseObserver.onError(
								Status.NOT_FOUND
								.withDescription("laptop ID does not exists")
								.asRuntimeException()
						);
					}
					return;
				}

				// 图片的数据字段
				ByteString chunkData = request.getChunkData();
				logger.info("receive image chunk with size: " + chunkData.size());

				if (imageData == null){
					logger.info("image info wasn't sent before");
					responseObserver.onError(
							Status.INVALID_ARGUMENT
							.withDescription("image info wasn't sent before")
							.asRuntimeException()
					);
					return;
				}

				// 传输图片chunk时，统计图片总大小
				int size = imageData.size() + chunkData.size();
				// 如果图片过大，则终止传输
				if (size > maxImageSize) {
					logger.info("image is too large: " + size);
					responseObserver.onError(
							Status.INVALID_ARGUMENT
							.withDescription("image is too large: " + size)
							.asRuntimeException()
					);
					return;
				}

				try {
					// 将数据流写入到输出流
					chunkData.writeTo(imageData);
				} catch (IOException e) {
					responseObserver.onError(
							Status.INTERNAL
							.withDescription("cannot write chunk data:" + e.getMessage())
							.asRuntimeException()
					);
					return;
				}
			}

			@Override
			public void onError(Throwable t) {
				logger.warning(t.getMessage());
			}

			// 这个函数被调用时，代表server已经收到了所有的image chunk data
			@Override
			public void onCompleted() {
				String imageID = "";
				int imageSize = imageData.size();

				try {
					imageID = imageStore.Save(laptopID,imageType,imageData);
				} catch (IOException e) {
					responseObserver.onError(
							Status.INTERNAL
							.withDescription("cannot save image to the store:" + e.getMessage())
							.asRuntimeException()
					);
				}

				UploadImageResponse response = UploadImageResponse.newBuilder()
						.setId(imageID)
						.setSize(imageSize)
						.build();
				responseObserver.onNext(response);
				responseObserver.onCompleted();
			}
		};
	}
}
