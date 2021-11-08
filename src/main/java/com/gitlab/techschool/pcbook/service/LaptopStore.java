package com.gitlab.techschool.pcbook.service;

import com.gitlab.techschool.pcbook.pb.Filter;
import com.gitlab.techschool.pcbook.pb.Laptop;
import io.grpc.Context;

/**
 * @author yuhao
 * @date: 2021/11/6
 * @description: 存储Laptop的接口，不同的实现方式可以自行实现本接口
 */
public interface LaptopStore {
	// 存储Laptop
	void Save(Laptop laptop) throws Exception;
	// 在存储中查找指定id的laptop
	Laptop Find(String id);
	// 查找符合条件的laptop
	void Search(Context ctx, Filter filter, LaptopStream stream);
}
