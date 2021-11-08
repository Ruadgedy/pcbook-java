package com.gitlab.techschool.pcbook.service;


import com.gitlab.techschool.pcbook.pb.*;
import io.grpc.Context;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author yuhao
 * @date: 2021/11/6
 * @description:
 */
public class LaptopService extends LaptopServiceGrpc.LaptopServiceImplBase {

	private static final Logger logger = Logger.getLogger(LaptopService.class.getName());

	private LaptopStore store;

	public LaptopService(LaptopStore store) {
		this.store = store;
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
			store.Save(other);
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
		// 传递响应
		responseObserver.onNext(response);
		// 告诉客户端，rpc通信结束
		responseObserver.onCompleted();

		logger.info("saved laptop with ID: " + other.getId());
	}

	@Override
	public void searchLaptop(SearchLaptopRequest request, StreamObserver<SearchLaptopResponse> responseObserver) {
		Filter filter = request.getFilter();
		logger.info("got a search-laptop request with filter:\n" + filter);

		store.Search(Context.current(), filter,laptop -> {
			logger.info("found laptop with ID: " + laptop.getId());
			SearchLaptopResponse response = SearchLaptopResponse.newBuilder().setLaptop(laptop).build();
			responseObserver.onNext(response);
		});

		responseObserver.onCompleted();
		logger.info("search laptop complete");
	}
}
