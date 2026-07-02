package com.auth.student_management.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CertificateDto {

	
	private Long id;
    private String certificateName;
    private LocalDate  issueDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCertificateName() {
		return certificateName;
	}
	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}
	public LocalDate getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(LocalDate  issueDate) {
		this.issueDate = issueDate;
	}
    
}
