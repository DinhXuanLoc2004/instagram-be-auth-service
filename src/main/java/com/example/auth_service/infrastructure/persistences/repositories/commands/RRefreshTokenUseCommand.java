package com.example.auth_service.infrastructure.persistences.repositories.commands;

import java.time.Duration;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class RRefreshTokenUseCommand {
    private final RedisOperations<byte[], String> redisOperations;

    public Boolean saveIfAbsent(byte[] key, String value, Duration ttl){
        return redisOperations.opsForValue().setIfAbsent(key, value, ttl);
    }

    public Boolean delete(byte[] key){
        return redisOperations.delete(key);
    }
}
