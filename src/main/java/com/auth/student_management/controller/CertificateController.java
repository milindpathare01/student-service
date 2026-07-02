package com.auth.student_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.student_management.entity.certificate;
import com.auth.student_management.service.CertificateService;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

	
	@Autowired
	private CertificateService service;
	
	@PostMapping
	public certificate saveCertificate(@RequestBody certificate cert) {
		
		return service.saveCertificate(cert);
	}
	
	
	@GetMapping("/{studentId}")
	public List<certificate> getcertificateByID(@PathVariable Long studentId) {
		
		return service.getcertById(studentId);
	}
	
	

}
