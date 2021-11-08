package com.gitlab.techschool.pcbook.service;

/**
 * @author yuhao
 * @date: 2021/11/6
 * @description:
 */
public class AlreadyExistsException extends RuntimeException {
	public AlreadyExistsException(String s) {
		super(s);
	}
}
