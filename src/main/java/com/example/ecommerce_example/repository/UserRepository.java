package com.example.ecommerce_example.repository;

import com.example.ecommerce_example.entity.User; // CORRECT: use your own entity
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
