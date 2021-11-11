package com.gitlab.techschool.pcbook.service;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 * @author yuhao
 * @date: 2021/11/10
 * @description:
 */
public class InMemoryRatingStoreTest {

	@Test
	public void add() throws InterruptedException {
		InMemoryRatingStore ratingStore = new InMemoryRatingStore();

		List<Callable<Rating>> tasks = new LinkedList<>();
		String laptopID = UUID.randomUUID().toString();
		double score = 5;

		int n = 10;
		for (int i = 0; i < n; i++) {
			tasks.add(() -> ratingStore.Add(laptopID,score));
		}

		Set<Integer> ratedCount = new HashSet<>();
		Executors.newWorkStealingPool()
				.invokeAll(tasks)
				.stream()
				.forEach(future -> {
					Rating rating = null;
					try {
						rating = future.get();
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
					assertEquals(rating.getSum(),rating.getCount()*score, 1e-9);
					ratedCount.add(rating.getCount());
				});

		assertEquals(n, ratedCount.size());
		for (int i = 1; i < n; i++) {
			assertTrue(ratedCount.contains(i));
			// export http_proxy=http://127.0.0.1:1087;export https_proxy=http://127.0.0.1:1087;
		}
	}
}
