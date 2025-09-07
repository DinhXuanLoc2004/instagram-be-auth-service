package com.example.auth_service.infrastructure.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HashService {
    private final PasswordEncoder passwordEncoder;

    public String hash(String raw){
        return passwordEncoder.encode(raw);
    }

    public boolean verify(String raw, String hashed){
        return passwordEncoder.matches(raw, hashed);
    }
}
