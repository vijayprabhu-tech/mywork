package com.bnk.ecomproj.controller;

import com.bnk.ecomproj.dto.PaymentRequest;
import com.bnk.ecomproj.dto.PaymentResponse;
import com.bnk.ecomproj.dto.PaymentSessionResponse;
import com.bnk.ecomproj.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentServices;

    @PostMapping(value = "/payment-sessions")
    public PaymentSessionResponse createSession() {
        return paymentServices.createSession();
    }

    @PostMapping(value = "/payments")
    public PaymentResponse makePayment(@RequestHeader("Idempotency-Key") String idempotencyKey,
                                       @RequestBody PaymentRequest request) {
        try {
            return paymentServices.processPayment(request, idempotencyKey);

        } catch (Exception e) {
            System.err.println("Error during processing payment: " + e.getMessage());
            return new PaymentResponse(e.getMessage());
        }
    }
}
