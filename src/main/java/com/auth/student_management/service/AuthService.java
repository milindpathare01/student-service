package com.auth.student_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.student_management.dto.AuthResponse;
import com.auth.student_management.dto.LoginRequest;
import com.auth.student_management.dto.RegisterRequest;
import com.auth.student_management.entity.User;
import com.auth.student_management.repository.UserRepository;

@Service
public class AuthService {

	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwts;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public String register(RegisterRequest request) {
		
		if(repo.existsByUsername(request.getUsername())) {
			return "username already Exist";
		}
		
		User user = new User();
		
		user.setUsername(request.getUsername());
		 user.setEmail(request.getEmail());
		 
		 user.setPassword(passwordEncoder.encode(request.getPassword()));
		 
		 user.setrole("USER");
		 
		 repo.save(user);
		 
		 return "User Registerd successfully";
		
	}
	
	
	public AuthResponse Login(LoginRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken
				(request.getUsername(), request.getPassword()));
		
		String token = jwts.generateToken(request.getUsername());
		
		return new AuthResponse(token);
		
	}
	
	
	
	
	
}
