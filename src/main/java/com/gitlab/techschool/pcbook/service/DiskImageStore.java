package com.gitlab.techschool.pcbook.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author yuhao
 * @date: 2021/11/9
 * @description:
 */
public class DiskImageStore implements ImageStore {

	private String imageFolder;
	private ConcurrentMap<String, ImageMetadata> data;

	public DiskImageStore(String imageFolder) {
		this.imageFolder = imageFolder;
		this.data = new ConcurrentHashMap<>(0);
	}

	@Override
	public String Save(String laptopID, String imageType, ByteArrayOutputStream imageData) throws IOException {
		String imageID = UUID.randomUUID().toString();
		String filepath = String.format("%s/%s%s", imageFolder, imageID, imageType);

		FileOutputStream fos = new FileOutputStream(filepath);
		imageData.writeTo(fos);
		fos.close();

		ImageMetadata metadata = new ImageMetadata(laptopID, imageType, filepath);
		data.put(imageID,metadata);

		return imageID;
	}
}
