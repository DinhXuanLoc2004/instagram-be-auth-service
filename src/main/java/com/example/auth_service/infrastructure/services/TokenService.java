package com.example.auth_service.infrastructure.services;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.ports.outputs.JWTOutput;

@Component
public class TokenService {
    public JWTOutput generateToken(String accountId){
        return new JWTOutput(accountId, accountId);
    }
}
