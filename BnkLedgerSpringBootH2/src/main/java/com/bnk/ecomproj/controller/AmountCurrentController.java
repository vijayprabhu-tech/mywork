package com.bnk.ecomproj.controller;

import com.bnk.ecomproj.model.AmountCurrent;
import com.bnk.ecomproj.service.AmountCurrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AmountCurrentController {

    @Autowired
    private AmountCurrentService service;

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


}
