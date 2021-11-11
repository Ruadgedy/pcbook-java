package com.gitlab.techschool.pcbook.service;

/**
 * @author yuhao
 * @date: 2021/11/10
 * @description:
 */
public interface RatingStore {
	Rating Add(String laptopID, double score);
}
