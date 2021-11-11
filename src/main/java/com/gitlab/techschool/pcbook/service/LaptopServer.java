package com.gitlab.techschool.pcbook.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author yuhao
 * @date: 2021/11/6
 * @description:
 */
public class LaptopServer {

	private static final  Logger logger = Logger.getLogger(LaptopServer.class.getName());

	private final int port;
	private final Server server;

	public LaptopServer(int port, LaptopStore laptopStore, ImageStore imageStore, RatingStore ratingStore) {
		this(ServerBuilder.forPort(port),port, laptopStore, imageStore, ratingStore);
	}

	// ServerBuilder是为了方便后面做单元测试
	public LaptopServer(ServerBuilder serverBuilder, int port, LaptopStore laptopStore, ImageStore imageStore, RatingStore ratingStore){
		this.port = port;
		LaptopService laptopService = new LaptopService(laptopStore, imageStore, ratingStore);
		this.server = serverBuilder.addService(laptopService).build();
	}

	public void start() throws IOException{
		server.start();
		logger.info("server started on port " + port);

		Runtime.getRuntime().addShutdownHook(
				new Thread(){
					@Override
					public void run() {
						System.err.println("shut down gRPC server because JVM shuts down");
						try {
							LaptopServer.this.stop();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.err.println("server shut down");
					}
				}
		);
	}

	public void stop() throws InterruptedException {
		if (server != null){
			server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
		}
	}

	private void blockUntilShutdown() throws InterruptedException {
		if (server != null){
			server.awaitTermination();
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		InMemoryLaptopStore laptopStore = new InMemoryLaptopStore();
		DiskImageStore imageStore = new DiskImageStore("img");
		RatingStore ratingStore = new InMemoryRatingStore();

		LaptopServer server = new LaptopServer(8080, laptopStore, imageStore,ratingStore);
		server.start();
		server.blockUntilShutdown();
	}
}
