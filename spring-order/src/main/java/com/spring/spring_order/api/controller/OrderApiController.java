package com.spring.spring_order.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderApiController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/getOrder")
	public String getOrder() {
		String url = "http://member/getMember";
		String result = restTemplate.getForObject(url, String.class);
		return result;
	}
}
