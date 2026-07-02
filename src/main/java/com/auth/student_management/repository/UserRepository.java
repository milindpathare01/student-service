package com.auth.student_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.student_management.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
