package com.gitlab.techschool.pcbook.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author yuhao
 * @date: 2021/11/9
 * @description: 定义图像存储接口
 */
public interface ImageStore {
	String Save(String laptopID, String imageType, ByteArrayOutputStream imageData) throws IOException;
}
