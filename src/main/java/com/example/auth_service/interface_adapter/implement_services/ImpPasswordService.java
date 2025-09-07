package com.example.auth_service.interface_adapter.implement_services;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_services.IPasswordService;
import com.example.auth_service.infrastructure.services.HashService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ImpPasswordService implements IPasswordService{

    private final HashService hashService;

    @Override
    public String hash(String raw) {
        return hashService.hash(raw);
    }

    @Override
    public boolean verify(String raw, String hashed) {
        return hashService.verify(raw, hashed);
    }
    
}
