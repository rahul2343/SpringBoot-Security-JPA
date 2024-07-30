package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.entities.CustomUserDetails;
import com.springboot.entities.User;
import com.springboot.repo.UserRepositary;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepositary repositary;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repositary.findByUsername(username);
		
		if(user == null) throw new UsernameNotFoundException("No User");
		
		return new CustomUserDetails(user);
	}

}
