package com.auth.student_management.dto;

import java.util.List;

import com.auth.student_management.entity.Student;
import com.auth.student_management.entity.certificate;

public class StudentResponseDto {
	
	  private Student student;
	  private List<CertificateDto> certificates;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<CertificateDto> getCertificates() {
		return certificates;
	}
	public void setCertificates(List<CertificateDto> certificates) {
		this.certificates = certificates;
	}

}
