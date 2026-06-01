package com.bnk.ecomproj.service;

import com.bnk.ecomproj.model.AmountCurrent;
import com.bnk.ecomproj.repo.AmountCurrentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmountCurrentService {

    @Autowired
    private AmountCurrentRepo repo;

    public List<AmountCurrent> getAllEvents() {
        return repo.findAll();
    }

    public List<AmountCurrent> getEvent(String id) {
        System.out.println("----------- "+id);
        return repo.findByEventId(id);
    }

    public List<AmountCurrent> getAllAccounts() {
        return repo.findAll();
    }

    public List<AmountCurrent> getAccount(String id) {
        System.out.println("----------- "+id);
        return repo.findByAccountId(id);
    }
}
