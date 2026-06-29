package com.bnk.ecomproj.controller;

import com.bnk.ecomproj.dto.PaymentRequest;
import com.bnk.ecomproj.dto.PaymentResponse;
import com.bnk.ecomproj.dto.PaymentSessionResponse;
import com.bnk.ecomproj.model.AmountCurrent;
import com.bnk.ecomproj.service.AmountCurrentService;
import com.bnk.ecomproj.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AmountCurrentController {

    @Autowired
    private AmountCurrentService service;

    @Autowired
    private PaymentService PayServices;

    @RequestMapping("/hello")
    public String greet() {
        return "Hello World";
    }


    @GetMapping("/events")
    public List<AmountCurrent> getAllEvents() {
        return service.getAllEvents();
    }

    @GetMapping("/events/{id}")
    public List<AmountCurrent> getEvent(@PathVariable String id) {
        return service.getEvent(id);
    }

    @GetMapping("/accounts")
    public List<AmountCurrent> getAllAccounts() {
        return service.getAllAccounts();
    }

    @GetMapping("/accounts/{id}")
    public List<AmountCurrent> getAccount(@PathVariable String id) {
        return service.getAccount(id);
    }

    @GetMapping("/accounts/{id}/balance")
    public double getAccountBalance(@PathVariable String id) {
        List<AmountCurrent> ac = service.getAccount(id);

       // double balance = ac.stream()
         //       .mapToDouble(account -> account.getType() .equals("CREDIT"))..sum() - account.getDebitAmount())
           //     .sum();
        double balance = 0;
        double credit = ac.stream()
                .collect(Collectors.summarizingDouble(account ->
                        "CREDIT".equalsIgnoreCase(account.getType()) ? account.getAmount() : 0
                ))
                .getSum();

        double debit = ac.stream()
                .collect(Collectors.summarizingDouble(account ->
                        "DEBIT".equalsIgnoreCase(account.getType()) ? account.getAmount() : 0
                ))
                .getSum();
        System.out.println(" ----------> Total CREDIT = "+credit);
        System.out.println(" ----------> Total DEBIT = "+debit);
        balance = credit - debit;
        System.out.println(" ----------> Balance = "+balance);
        return balance;

    }

    /// //////////////////////////////////



    @PostMapping(value = "/payment-sessions")
    public PaymentSessionResponse createSession() {
        return PayServices.createSession();
    }

    @PostMapping(value = "/payments")
    public PaymentResponse makePayment(@RequestHeader("Idempotency-Key") String idempotencyKey,
                                       @RequestBody PaymentRequest request) {
        try {
			return PayServices.processPayment(request, idempotencyKey);
    //        return service.processPaymentWithLockMech(request, idempotencyKey);
        } catch (Exception e) {
            System.err.println("Error during processing payment: " + e.getMessage());
            return new PaymentResponse(e.getMessage());
        }
    }

}
