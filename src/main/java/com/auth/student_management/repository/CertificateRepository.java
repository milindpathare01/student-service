package com.auth.student_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.student_management.entity.certificate;


@Repository
public interface CertificateRepository extends JpaRepository<certificate, Long>{
	
	
	List<certificate> findBystudentId(Long studentId);
}
