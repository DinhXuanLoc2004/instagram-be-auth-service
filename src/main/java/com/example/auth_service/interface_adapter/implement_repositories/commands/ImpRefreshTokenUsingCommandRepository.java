package com.example.auth_service.interface_adapter.implement_repositories.commands;

import java.time.Duration;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.commands.IRefreshTokenUsingCommandRepository;
import com.example.auth_service.domain.entities.RefreshTokenUseEntity;
import com.example.auth_service.infrastructure.services.MessageDigestService;
import com.example.auth_service.infrastructure.services.RedisService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ImpRefreshTokenUsingCommandRepository implements IRefreshTokenUsingCommandRepository{

    private final RedisService redisService;
    private final MessageDigestService digestService;

    @Override
    public void save(RefreshTokenUseEntity data) {
        final byte[] refreshTokenHashByted = digestService.hashWithSHA256(data.getId().toString());

        redisService.saveKey(refreshTokenHashByted, data.getAccountId().toString(), Duration.ofDays(30));
    }

    @Override
    public void delete(UUID id) {
        final byte[] refreshTokenHashByted = digestService.hashWithSHA256(id.toString());

        redisService.deleteKey(refreshTokenHashByted);
    }
    
}
