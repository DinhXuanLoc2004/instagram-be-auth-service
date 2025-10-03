package com.example.auth_service.infrastructure.services;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RedisService {
    private RedisOperations<byte[], String> redisOperations;

    public void saveKey(byte[] key, String value, Duration ttl) {
        redisOperations.opsForValue().set(key, value, ttl);
    }

    public String getValue(byte[] key) {
        return redisOperations.opsForValue().get(key);
    }

    public void deleteKey(byte[] key) {
        redisOperations.delete(key);
    }
}
