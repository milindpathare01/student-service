package com.auth.student_management.service;


import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.auth.student_management.dto.CertificateDto;
import com.auth.student_management.dto.StudentDetailsResponse;
import com.auth.student_management.dto.StudentResponseDto;
import com.auth.student_management.entity.Student;
import com.auth.student_management.entity.certificate;
import com.auth.student_management.exceptions.ResourceNotFoundException;
import com.auth.student_management.external.CertificateClient;
import com.auth.student_management.repository.StudentRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;

@Service
public class StudentService {

	
	private static final Logger logger =
            org.slf4j.LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private CertificateClient certificateClient;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public Student saveStudent(Student student) {
	
	
		return repository.save(student);
	}
	

	public List<Student> getAllStudent(){
		
		return repository.findAll();
	}
	
	@CircuitBreaker(name = "certificateService", fallbackMethod = "certificateFallback")
	@Retry(name = "certificateService",fallbackMethod = "retryFallback")
	public StudentDetailsResponse getStudentwithCertificate(Long id) {
		
		Student student =  repository.findById(id).orElseThrow( () -> new RuntimeException("Student not Found"));
		
//		certificate[] certificates =
//				restTemplate.getForObject("http://localhost:8081/certificate/" + id,
//                        certificate[].class);
		logger.info("Calling Certificate Service for student id: {}", id);		
		List<CertificateDto> certificateDtos =
		        certificateClient.getCertificateBystudentId(id);
		
		StudentDetailsResponse studentDetailsResponse = new StudentDetailsResponse();
		
		studentDetailsResponse.setStudent(student);
		studentDetailsResponse.setCertificates(certificateDtos);
		
		return studentDetailsResponse;
	
	}
	
	public StudentResponseDto getStudentResponseDto(Long id) {
		
		   Student student = repository.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException(
		                    "Student not found"));
		   List<CertificateDto>  certificateDtos = certificateClient.getCertificateBystudentId(id);
		  
		   StudentResponseDto response = new StudentResponseDto();
		   response.setStudent(student);
		   response.setCertificates(certificateDtos);
		   
		return response;
	}
	
	public StudentDetailsResponse certificateFallback(Long id,Throwable ex) {
		
		Student student = repository.findById(id).orElseThrow(()-> new RuntimeException("Stundent Not Found"));
		
		StudentDetailsResponse response = new StudentDetailsResponse();
		logger.error("Certificate Service is down. Fallback executed for student id: {}", id);
		response.setStudent(student);
		response.setCertificates(List.of());
		
		return response;
	}
	
	public StudentDetailsResponse retryFallback(Long id, Throwable ex) {
		logger.warn("Retry failed after max attempts for student id: {}", id);

		return certificateFallback(id, ex);

		}
}
