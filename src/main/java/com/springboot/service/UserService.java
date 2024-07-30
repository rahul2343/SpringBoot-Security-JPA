package com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.User;
import com.springboot.repo.UserRepositary;


@Service
public class UserService {

	@Autowired
	UserRepositary repositary;

	public UserService() {
		
	}

	public List<User> getAllUsers() {
		return repositary.findAll();
	}
	
	public User getUser(String username) {
		
		return repositary.findByUsername(username);	
	}
	
	public User addUser(User user) {
		User user1 = repositary.save(user);
		return user1;
	}

}
