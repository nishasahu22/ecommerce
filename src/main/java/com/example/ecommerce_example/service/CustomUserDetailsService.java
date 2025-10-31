package com.example.ecommerce_example.service;

import com.example.ecommerce_example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
    public class CustomUserDetailsService implements UserDetailsService {
        @Autowired
        private UserRepository userRepository;
        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            com.example.ecommerce_example.entity.User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
            );
        }
    }

