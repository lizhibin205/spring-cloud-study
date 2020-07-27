package com.bytrees.cloud.controller;

@RestController
public class HelloController {
	@GetMapping("/helo")
	public String sayHello() {
		StringBuilder strBuilder = new StringBuilder(16);
		
		return "hello world!";
	}
}
