package com.bookstore.repository;

import com.bookstore.model.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<Verification, Long> {
    Optional<Verification> findByVerificationCode(String verificationCode);
}
