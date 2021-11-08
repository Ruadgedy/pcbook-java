package com.gitlab.techschool.pcbook.service;

import com.gitlab.techschool.pcbook.pb.*;
import com.gitlab.techschool.pcbook.sample.Generator;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;

import java.util.Iterator;
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
	private final LaptopServiceGrpc.LaptopServiceBlockingStub blockingStub;

	public LaptopClient(String host, int port){
		channel = ManagedChannelBuilder.forAddress(host,port)
				.usePlaintext() // 使用明文传输，不用ssl
				.build();

		blockingStub = LaptopServiceGrpc.newBlockingStub(channel);
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

	public static void main(String[] args) throws InterruptedException {
		LaptopClient client = new LaptopClient("0.0.0.0", 8080);

		Generator generator = new Generator();

		try {
			for (int i = 0; i < 10; i++) {
				Laptop laptop = generator.NewLaptop();
				client.createLaptop(laptop);
			}

			Memory minRam = Memory.newBuilder()
					.setValue(8)
					.setUnit(Memory.Unit.GIGABYTE)
					.build();

			Filter filter = Filter.newBuilder()
					.setMaxPriceUsd(3000)
					.setMinCpuCores(4)
					.setMinCpuGhz(2.5)
					.setMinRam(minRam)
					.build();

			client.searchLaptop(filter);
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
