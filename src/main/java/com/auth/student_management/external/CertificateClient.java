package com.auth.student_management.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.auth.student_management.dto.CertificateDto;

@FeignClient(name = "CERTIFICATE-SERVICE")
public interface CertificateClient {
	
	@GetMapping("/certificate/student/{studentId}")
	List<CertificateDto> getCertificateBystudentId(@PathVariable Long studentId);

}
