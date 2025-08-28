package com.example.auth_service.application.service_for_usecase;

import com.example.auth_service.application.ports.outputs.LoginOutput;

public interface ITokenService {
    public LoginOutput generateToken(String idUser);
}
