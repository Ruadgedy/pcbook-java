package com.gitlab.techschool.pcbook.service;

import com.gitlab.techschool.pcbook.pb.*;
import com.gitlab.techschool.pcbook.sample.Generator;
import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author yuhao
 * @date: 2021/11/6
 * @description:
 */
public class LaptopServerTest {

	// automatic graceful shutdown channel at the end of test
	@Rule
	public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

	private LaptopStore laptopStore;
	private LaptopServer server;
	private ManagedChannel channel;
	private ImageStore imageStore;
	private RatingStore ratingStore;

	@Before
	public void setUp() throws Exception {
		String serverName = InProcessServerBuilder.generateName();
		InProcessServerBuilder serverBuilder = InProcessServerBuilder.forName(serverName).directExecutor();

		laptopStore = new InMemoryLaptopStore();
		imageStore = new DiskImageStore("img");
		ratingStore = new InMemoryRatingStore();
		server = new LaptopServer(serverBuilder,0,laptopStore, imageStore,ratingStore);
		server.start();

		channel = grpcCleanup.register(
				InProcessChannelBuilder.forName(serverName).directExecutor().build()
		);
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void createLaptopWithValidID(){
		Generator generator = new Generator();
		Laptop laptop = generator.NewLaptop();

		CreateLaptopRequest request = CreateLaptopRequest.newBuilder().setLaptop(laptop).build();
		LaptopServiceGrpc.LaptopServiceBlockingStub stub = LaptopServiceGrpc.newBlockingStub(channel);
		CreateLaptopResponse response = stub.createLaptop(request);
		assertNotNull(response);
		assertEquals(laptop.getId(), response.getId());

		Laptop find = laptopStore.Find(response.getId());
		assertNotNull(find);
	}

	@Test
	public void rateLaptop() throws Exception {
		Generator generator = new Generator();
		Laptop laptop = generator.NewLaptop();
		laptopStore.Save(laptop);

		LaptopServiceGrpc.LaptopServiceStub stub = LaptopServiceGrpc.newStub(channel);
		StreamObserver<RateLaptopResponse> responseStreamObserver = new StreamObserver<RateLaptopResponse>() {
			public List<RateLaptopResponse> responses = new LinkedList<>();
			public Throwable err;
			public boolean completed;

			@Override
			public void onNext(RateLaptopResponse response) {
				responses.add(response);
			}

			@Override
			public void onError(Throwable t) {
				err = t;
			}

			@Override
			public void onCompleted() {
				completed = true;
			}
		};
		StreamObserver<RateLaptopRequest> requestStreamObserver = stub.rateLaptop(responseStreamObserver);

		double[] scores = {8, 7.5, 10};
		double[] average = {8, 7.75, 8.5};
		int n = scores.length;

		for (int i = 0; i < n; i++) {
			RateLaptopRequest request = RateLaptopRequest.newBuilder()
					.setLaptopId(laptop.getId())
					.setScore(scores[i])
					.build();
			requestStreamObserver.onNext(request);
		}

		requestStreamObserver.onCompleted();
//		assertNull(responseStreamObserver.);
	}
}
