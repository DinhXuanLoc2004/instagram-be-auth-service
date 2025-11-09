package com.example.auth_service.infrastructure.persistences.repositories.queries;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class RRefreshTokenUseQuery {
    private final RedisOperations<byte[], String> redisOperations;

    public String getValue(byte[] key) {
        final String value = redisOperations.opsForValue().get(key);
        return value;
    }
}
