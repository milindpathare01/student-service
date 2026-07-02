package com.auth.student_management.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "certificates")
public class certificate {

	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Long studentId;

	    private String certificateName;

	    private LocalDate issueDate;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getstudentId() {
			return studentId;
		}

		public void setstudentId(Long studentId) {
			this.studentId = studentId;
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

		public void setIssueDate(LocalDate issueDate) {
			this.issueDate = issueDate;
		}
	    
	    

}
