package com.spring.spring_order.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("member")
public interface PersonClient {

	@GetMapping("/getMember")
	String  getMember();
}
