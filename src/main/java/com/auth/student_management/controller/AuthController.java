package com.auth.student_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.student_management.dto.AuthResponse;
import com.auth.student_management.dto.LoginRequest;
import com.auth.student_management.dto.RegisterRequest;
import com.auth.student_management.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@PostMapping("/register")
	public String register_Student(@RequestBody RegisterRequest request){
		
		 return service.register(request);
	}
	
	
	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest request) {
		
		
		return service.Login(request);
		
	}
}
