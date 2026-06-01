package com.bnk.ecomproj.service;

import com.bnk.ecomproj.model.AmountCurrent;
import com.bnk.ecomproj.model.Product;
import com.bnk.ecomproj.repo.AmountCurrentRepo;
import com.bnk.ecomproj.repo.ProductRepo;
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
}
