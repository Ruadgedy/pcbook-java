package com.gitlab.techschool.pcbook.service;

import com.gitlab.techschool.pcbook.pb.CreateLaptopRequest;
import com.gitlab.techschool.pcbook.pb.CreateLaptopResponse;
import com.gitlab.techschool.pcbook.pb.Laptop;
import com.gitlab.techschool.pcbook.pb.LaptopServiceGrpc;
import com.gitlab.techschool.pcbook.sample.Generator;
import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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

	@Before
	public void setUp() throws Exception {
		String serverName = InProcessServerBuilder.generateName();
		InProcessServerBuilder serverBuilder = InProcessServerBuilder.forName(serverName).directExecutor();

		laptopStore = new InMemoryLaptopStore();
		imageStore = new DiskImageStore("img");
		server = new LaptopServer(serverBuilder,0,laptopStore, imageStore);
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
}
