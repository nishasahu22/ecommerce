package com.example.ecommerce_example.controller;

import com.example.ecommerce_example.entity.User;
import com.example.ecommerce_example.repository.UserRepository;
import com.example.ecommerce_example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegister(@ModelAttribute User user, Model model) {
        if (authService.emailExists(user.getEmail())) {
            model.addAttribute("error", "Email already registered");
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER"); // default role
        userRepository.save(user);
        return "redirect:/login?registered=true";
    }

    @GetMapping("/default")
    public String defaultAfterLogin(Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/user/dashboard";
        }
    }
}

