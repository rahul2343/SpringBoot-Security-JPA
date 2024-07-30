package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entities.User;
import com.springboot.service.UserService;


@RestController
@RequestMapping("/users")
public class MyController {

	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/")
	public List<User> getAllUser() {
		System.out.println("gettting All data...");
		return userService.getAllUsers();
	}

	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		System.out.println("gettting Single data...");
		return userService.getUser(username);
	}
	
	@PostMapping("/")
	public User addUser(@RequestBody User user) {
		System.out.println("Requesting data to add....");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userService.addUser(user);

	}

}
