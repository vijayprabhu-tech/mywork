package com.bnk.ecomproj.repo;

import com.bnk.ecomproj.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
