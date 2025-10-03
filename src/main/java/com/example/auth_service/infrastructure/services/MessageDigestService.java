package com.example.auth_service.infrastructure.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MessageDigestService {
    private final MessageDigest digest;

    public byte[] hashWithSHA256(String value) {
        return digest.digest(value.getBytes(StandardCharsets.UTF_8));
    }
}
