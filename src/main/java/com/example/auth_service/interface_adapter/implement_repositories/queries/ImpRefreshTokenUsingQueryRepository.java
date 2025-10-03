package com.example.auth_service.interface_adapter.implement_repositories.queries;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.queries.IRefreshTokenUsingQueryReposetory;
import com.example.auth_service.infrastructure.services.MessageDigestService;
import com.example.auth_service.infrastructure.services.RedisService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ImpRefreshTokenUsingQueryRepository implements IRefreshTokenUsingQueryReposetory{

    private final MessageDigestService digestService;
    private final RedisService redisService;

    @Override
    public Optional<UUID> findAccountIdById(UUID idRefreshToken) {
        final byte[] key = digestService.hashWithSHA256(idRefreshToken.toString());

        final Optional<UUID> accountId = Optional.ofNullable(UUID.fromString(redisService.getValue(key)));

        return accountId;
    }
    
}
