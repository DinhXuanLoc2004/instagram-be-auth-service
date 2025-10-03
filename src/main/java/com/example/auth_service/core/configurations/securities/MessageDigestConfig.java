package com.example.auth_service.core.configurations.securities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageDigestConfig {
    private final String algorithmSHA256 = "SHA-256";

    @Bean
    public MessageDigest digest() throws NoSuchAlgorithmException{
        return MessageDigest.getInstance(algorithmSHA256);
    }
}
