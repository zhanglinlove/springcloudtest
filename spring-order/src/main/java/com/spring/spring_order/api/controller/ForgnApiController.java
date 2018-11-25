package com.spring.spring_order.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.spring_order.api.PersonClient;

@RestController
public class ForgnApiController {

	@Autowired
	private PersonClient personClient;
	
	@GetMapping("/test")
	public String getTest() {
		return personClient.getMember();
	}
}
