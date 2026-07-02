package com.auth.student_management.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.student_management.entity.certificate;
import com.auth.student_management.repository.CertificateRepository;
import com.auth.student_management.repository.StudentRepository;

@Service
public class CertificateService {

	@Autowired
	private CertificateRepository certificateRepository;
	
	 @Autowired
	 private StudentRepository studentRepository;
	

	public certificate saveCertificate(certificate certificate) {
		
		  boolean exists =
	                studentRepository.existsById(certificate.getstudentId());
		  
		  if (!exists) {
	            throw new RuntimeException("Student not found");
	        }

	        return certificateRepository.save(certificate);
    }
	
	
	public List<certificate> getcertById(Long studentId){
		return certificateRepository.findBystudentId(studentId);
	}
	
	
}
