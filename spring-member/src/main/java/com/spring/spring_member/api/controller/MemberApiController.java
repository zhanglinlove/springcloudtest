package com.spring.spring_member.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberApiController {

	@GetMapping("/getMember")
	public String  getMember() {
		
		return "this is member, springcloud2";
	}
}
