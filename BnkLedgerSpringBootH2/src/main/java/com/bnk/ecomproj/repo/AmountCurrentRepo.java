package com.bnk.ecomproj.repo;

import com.bnk.ecomproj.model.AmountCurrent;
import com.bnk.ecomproj.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmountCurrentRepo extends JpaRepository<AmountCurrent, Integer> {
}
