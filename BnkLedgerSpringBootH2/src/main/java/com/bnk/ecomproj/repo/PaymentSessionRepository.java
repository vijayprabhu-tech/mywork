package com.bnk.ecomproj.repo;

import java.util.Optional;

import com.bnk.ecomproj.model.PaymentSession;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentSessionRepository extends JpaRepository<PaymentSession, Long> {
    Optional<PaymentSession> findByPaymentSessionId(String paymentSessionId);
}
