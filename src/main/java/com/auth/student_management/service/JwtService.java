package com.auth.student_management.service;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	
	private static final String SECRET_KEY =
            "mysecretkeymysecretkeymysecretkey12345";
	
	
	
	
	  // Generate Token
    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis()
                                + 1000 * 60 * 10)) // 10 minutes
                .signWith(getSignKey())
                .compact();
    }
	
	 // Extract Username
    public String extractUsername(String token) {

        return extractAllClaims(token).getSubject();
    }
	   // Validate Token
    public boolean validateToken(
            String token,
            String username) {

        return extractUsername(token).equals(username)
                && !isTokenExpired(token);
    }
	
    private boolean isTokenExpired(String token) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }
	
	private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
	
	 private Key getSignKey() {

	        return Keys.hmacShaKeyFor(
	                SECRET_KEY.getBytes());
	    }
}
