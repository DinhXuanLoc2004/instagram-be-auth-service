package com.example.auth_service.application.service_for_usecase;

import com.example.auth_service.application.ports.outputs.SignInOutput;

public interface ITokenService {
    public SignInOutput generateToken(String idUser);
}
