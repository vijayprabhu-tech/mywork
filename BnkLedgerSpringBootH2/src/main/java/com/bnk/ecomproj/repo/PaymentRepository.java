package com.bnk.ecomproj.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bnk.ecomproj.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}
