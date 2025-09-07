package com.example.auth_service.application.interface_services;

import com.example.auth_service.application.ports.outputs.JWTOutput;

public interface ITokenService {
    public JWTOutput generateToken(String accountId);
}
