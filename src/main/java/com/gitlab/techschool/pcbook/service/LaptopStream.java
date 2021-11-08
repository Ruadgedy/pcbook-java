package com.gitlab.techschool.pcbook.service;

import com.gitlab.techschool.pcbook.pb.Laptop;

/**
 * @author yuhao
 * @date: 2021/11/8
 * @description:
 */
public interface LaptopStream {
	void Send(Laptop laptop);
}
