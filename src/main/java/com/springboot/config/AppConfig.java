package com.springboot.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.springboot.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// to configure the HTTP security rules for the application
		
		//HttpSecurity
		// is used to configure web-based security for specific HTTP requests. 
		//It is part of the spring-security-web module and allows developers to customize the security settings for their web applications using a fluent APIy
 		//control various aspects of web security
		//	- Authentication -  form-based authentication, HTTP Basic authentication, or integration with external authentication providers.
		//  - Authorization - Define access rules and authorization policies based on URL patterns, HTTP methods, or user roles and authorities.
		//  - Cross-Site Request Forgery (CSRF) Protection: Enable or disable CSRF protection, which helps prevent certain types of cross-site attacks
		//	- Session Management - session fixation protection, maximum session duration, and session concurrency control.

		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/public/**").permitAll()
			.antMatchers("/signin").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/signin")
			.loginProcessingUrl("/doLogin")
			.defaultSuccessUrl("/users/")
			.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/signin?logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
//		auth.inMemoryAuthentication().withUser("Rahul").password(passwordEncoder().encode("12345")).roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("Rajesh").password(passwordEncoder().encode("12345")).roles("NORMAL");
		

	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Authorization:
	//	Authorization is the process of determining what resources or actions a user, client, or system is allowed to access or perform. It answers the question "What are you allowed to do?".
	
	//Authentication:
	//	Authentication is the process of verifying the identity of a user, client, or system. It answers the question "Who are you?". 
	

}
