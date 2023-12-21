package com.bookstore.repository;

import com.bookstore.model.entity.Order;
import com.bookstore.model.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderReference(String orderReference);
}
