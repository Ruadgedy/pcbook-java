package com.gitlab.techschool.pcbook.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author yuhao
 * @date: 2021/11/10
 * @description:
 */
public class InMemoryRatingStore implements RatingStore {

	private ConcurrentMap<String, Rating> data;

	public InMemoryRatingStore() {
		this.data = new ConcurrentHashMap<>();
	}

	@Override
	public Rating Add(String laptopID, double score) {
		// Because there might be many requests to rate the same laptop at the same time, we use merge()
		return data.merge(laptopID,new Rating(1,score), Rating::add);
	}
}
