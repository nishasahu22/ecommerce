package com.example.ecommerce_example.repository;


import com.example.ecommerce_example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

