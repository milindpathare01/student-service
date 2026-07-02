package com.auth.student_management.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String message){
		super(message);
	}

}
