package com.gitlab.techschool.pcbook.serializer;


import com.gitlab.techschool.pcbook.pb.Laptop;
import com.gitlab.techschool.pcbook.sample.Generator;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author yuhao
 * @date: 2021/11/2
 * @description:
 */
public class SerializerTest {

	@Test
	public void writeBinaryFile() throws IOException {
		Laptop laptop = new Generator().NewLaptop();
		Serializer serializer = new Serializer();
		String binaryFile = "laptop.bin";

		serializer.WriteBinaryFile(laptop, binaryFile);

		Laptop laptop1 = serializer.ReadBinaryFile(binaryFile);
		System.out.println(laptop.equals(laptop1));
	}

	@Test
	public void writeJSONFile() throws IOException {
		Laptop laptop = new Generator().NewLaptop();
		final Serializer serializer = new Serializer();
		String name = "laptop.json";
		serializer.WriteJSONFile(laptop,name);

		Laptop laptop1 = serializer.ReadJSONFile(name);
	}
}
