package com.example.auth_service.interface_adapter.implement_services;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_services.ITokenService;
import com.example.auth_service.application.ports.outputs.JWTOutput;
import com.example.auth_service.infrastructure.services.TokenService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ImpTokenService implements ITokenService{

    private final TokenService tokenService;

    @Override
    public JWTOutput generateToken(String accountId) {
        return tokenService.generateToken(accountId);
    }
    
}
