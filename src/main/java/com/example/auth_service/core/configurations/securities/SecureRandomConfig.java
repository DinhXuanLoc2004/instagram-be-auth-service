package com.example.auth_service.core.configurations.securities;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecureRandomConfig {
    @Bean
    public SecureRandom secureRandom(){
        return new SecureRandom();
    }
}
