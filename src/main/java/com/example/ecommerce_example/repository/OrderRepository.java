package com.example.ecommerce_example.repository;

import com.example.ecommerce_example.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserEmail(String userEmail);

}
