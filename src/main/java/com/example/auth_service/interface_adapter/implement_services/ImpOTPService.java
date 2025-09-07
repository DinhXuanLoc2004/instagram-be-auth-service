package com.example.auth_service.interface_adapter.implement_services;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_services.IOTPService;
import com.example.auth_service.infrastructure.services.EmailService;
import com.example.auth_service.infrastructure.services.HashService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ImpOTPService implements IOTPService {

    private final HashService hashService;
    private final EmailService emailService;
    private final SecureRandom secureRandom;

    @Override
    public String hash(String raw) {
        return hashService.hash(raw);
    }

    @Override
    public boolean verify(String raw, String hashed) {
        return hashService.verify(raw, hashed);
    }

    @Override
    public void send(String content, String email) {
        emailService.send(content, email);
    }

    @SuppressWarnings("unused")
    @Override
    public String generateCode() {
        if (lengthCode < 4 || lengthCode > 6) {
            throw new IllegalArgumentException("OTP length must be between 4 and 6");
        }

        int max = (int) Math.pow(10, lengthCode);
        int number = secureRandom.nextInt(max);
        return String.format("%0" + lengthCode + "d", number);
    }

}
