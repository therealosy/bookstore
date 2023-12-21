package com.bookstore.repository;

import com.bookstore.model.entity.Payment;
import com.bookstore.model.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByPaymentReference(String paymentReference);
}
