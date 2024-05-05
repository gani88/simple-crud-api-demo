package com.enigmacamp.simplecrudapidemo.repository;

import com.enigmacamp.simplecrudapidemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
