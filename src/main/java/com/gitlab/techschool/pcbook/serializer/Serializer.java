package com.gitlab.techschool.pcbook.serializer;

import com.gitlab.techschool.pcbook.pb.Laptop;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

import java.io.*;

/**
 * @author yuhao
 * @date: 2021/11/2
 * @description:
 */
public class Serializer {

	// 序列化为二进制文件
	public void WriteBinaryFile(Laptop laptop, String filename) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(filename)){
			// 二进制序列化文件
			laptop.writeTo(fos);
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 从二进制文件反序列化
	public Laptop ReadBinaryFile(String filename) throws IOException {
		FileInputStream fis = new FileInputStream(filename);
		// 二进制反序列化
		Laptop laptop = Laptop.parseFrom(fis);
		fis.close();
		return laptop;
	}

	// 序列化为JSON文件
	public void WriteJSONFile(Laptop laptop, String filename) throws InvalidProtocolBufferException {
		// 定制JSON序列化样式
		JsonFormat.Printer printer = JsonFormat.printer()
				.includingDefaultValueFields()
				.preservingProtoFieldNames();

		// 得到JSON序列化字符串
		String jsonString = printer.print(laptop);

		try(FileOutputStream fos = new FileOutputStream(filename)) {
			fos.write(jsonString.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 从JSON文件反序列化
	public Laptop ReadJSONFile(String filename) throws IOException {
		JsonFormat.Parser parser = JsonFormat.parser();

		FileReader reader = new FileReader(filename);
		Laptop.Builder builder = Laptop.newBuilder();
		// 执行反序列化
		parser.merge(reader, builder);
		return builder.build();
	}
}
