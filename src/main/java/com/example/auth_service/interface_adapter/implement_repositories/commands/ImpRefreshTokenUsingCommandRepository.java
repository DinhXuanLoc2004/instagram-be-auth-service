package com.example.auth_service.interface_adapter.implement_repositories.commands;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.commands.IRefreshTokenUsingCommandRepository;
import com.example.auth_service.domain.entities.RefreshTokenUseEntity;
import com.example.auth_service.infrastructure.persistences.repositories.commands.RRefreshTokenUseCommand;
import com.example.auth_service.infrastructure.services.MessageDigestService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ImpRefreshTokenUsingCommandRepository implements IRefreshTokenUsingCommandRepository {

    private final RRefreshTokenUseCommand refreshTokenUseCommand;
    private final MessageDigestService digestService;

    @Override
    public Boolean saveIfAbsent(RefreshTokenUseEntity entity) {
        final byte[] refreshTokenHashByted = digestService.hashWithSHA256(entity.getId().toString());

        return refreshTokenUseCommand.saveIfAbsent(refreshTokenHashByted, entity.getAccountId().toString(),
                entity.getExpireDuration());
    }

    @Override
    public Boolean delete(UUID id) {
        final byte[] refreshTokenHashByted = digestService.hashWithSHA256(id.toString());

        return refreshTokenUseCommand.delete(refreshTokenHashByted);
    }

}
