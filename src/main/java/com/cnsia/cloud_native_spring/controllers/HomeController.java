package com.cnsia.cloud_native_spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String getGreeting() {
		return "Welcome to the book catalog!";
	}

}
