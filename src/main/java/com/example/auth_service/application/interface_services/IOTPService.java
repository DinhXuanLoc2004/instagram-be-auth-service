package com.example.auth_service.application.interface_services;

public interface IOTPService extends IHashBaseService, IEmailService{
    public final int lengthCode = 6;
    public String generateCode();
}
