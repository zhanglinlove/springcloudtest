package com.springtest.spring_redission.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtest.spring_redission.config.RedissonLock;

@RestController
public class TestController {

	@Autowired
	private RedissonLock redissonLock;
	@Autowired
	private RedisTemplate redisTemplate;
	
	@GetMapping("/add")
	public String addOne() {
		redissonLock.lock("add");
		System.out.println("come in:" + System.currentTimeMillis());
		try {
			Object obj = redisTemplate.opsForValue().get("number");
			if (obj == null) {
				redisTemplate.opsForValue().set("number", 1);
			} else {
				redisTemplate.opsForValue().set("number", Integer.valueOf(obj.toString()) + 1);
			}
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (Exception e) {
			System.out.println(e);
			
		} finally {
			System.out.println("go out:" + System.currentTimeMillis());
			redissonLock.unlock("add");
		}
		return "success";
	}
	
	@GetMapping("/test")
	public void test() {
		int num = 50;
		for (int i = 0; i < num; i++) {
			addOne();
		}
	}
}
