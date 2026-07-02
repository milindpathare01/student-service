package com.auth.student_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.student_management.dto.StudentDetailsResponse;
import com.auth.student_management.dto.StudentResponseDto;
import com.auth.student_management.entity.Student;
import com.auth.student_management.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	
	@PostMapping()
	public Student savestudent(@RequestBody Student student) {
		
		return service.saveStudent(student);
	}
	
	@GetMapping
	public List<Student> getAllStudent() {
		return service.getAllStudent();
	}
	
	@GetMapping("/{id}/details")
	public StudentDetailsResponse getStudentDetails(@PathVariable Long id) {
	    return service.getStudentwithCertificate(id);
	}
	

}
