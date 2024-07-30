package com.springboot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entities.User;

public interface UserRepositary extends JpaRepository<User, String> {
	
	public abstract User findByUsername(String username);

}
