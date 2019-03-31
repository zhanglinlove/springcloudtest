package com.springtest.spring_config_cli.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Value("${from}")
	String name;
	
	@GetMapping("/hello")
	public String getInfo() {
		return name;
	}
}
