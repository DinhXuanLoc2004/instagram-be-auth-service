package com.example.auth_service.interface_adapter.implement_repositories.queries;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.queries.IRefreshTokenUsingQueryReposetory;
import com.example.auth_service.infrastructure.persistences.repositories.queries.RRefreshTokenUseQuery;
import com.example.auth_service.infrastructure.services.MessageDigestService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ImpRefreshTokenUsingQueryRepository implements IRefreshTokenUsingQueryReposetory{

    private final MessageDigestService digestService;
    private final RRefreshTokenUseQuery refreshTokenUseQuery;

    @Override
    public Optional<UUID> findAccountIdById(UUID idRefreshToken) {
        final byte[] key = digestService.hashWithSHA256(idRefreshToken.toString());

        final Optional<UUID> accountId = Optional.ofNullable(UUID.fromString(refreshTokenUseQuery.getValue(key)));

        return accountId;
    }
    
}
