package com.cnsia.cloud_native_spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnsia.cloud_native_spring.config.CloudNativeSpringConfig;

@RestController
public class HomeController {
	private final CloudNativeSpringConfig config;

	public HomeController(CloudNativeSpringConfig config) {
		this.config = config;
	}

	@GetMapping("/")
	public String getGreeting() {
		return config.getApplicationName();
	}

}
