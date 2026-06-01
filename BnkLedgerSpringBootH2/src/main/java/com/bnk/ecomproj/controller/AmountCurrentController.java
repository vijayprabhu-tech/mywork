package com.bnk.ecomproj.controller;

import com.bnk.ecomproj.model.AmountCurrent;
import com.bnk.ecomproj.service.AmountCurrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<AmountCurrent> getAccountBalance(@PathVariable String id) {
        return service.getAccount(id);
    }


}
