package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/signin")
	public String signin() {
		System.out.println("Signin accessed...");
		return "login";
	}
	
	
	@GetMapping("/service")
	public String service() {
		System.out.println("Service accessed...");
		return "service";
	}
	
	@GetMapping("/logouturl")
	public String logout() {
		System.out.println("logout.....");
		return "logout";
	}
	

}
