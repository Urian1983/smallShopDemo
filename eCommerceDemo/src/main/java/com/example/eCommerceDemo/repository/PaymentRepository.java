package com.example.eCommerceDemo.repository;

import com.example.eCommerceDemo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByOrderId(Long orderId);
    Optional<Payment> findByUserId(Long userId);
}
