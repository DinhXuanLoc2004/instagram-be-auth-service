package com.example.auth_service.application.service_for_usecase;

public interface IOTPService extends IHashBaseService, IEmailService{
    public String generateCode();
}
