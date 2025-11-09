package com.example.auth_service.application.interface_repositories.commands;

import java.util.UUID;

import com.example.auth_service.domain.entities.RefreshTokenUseEntity;

public interface IRefreshTokenUsingCommandRepository{
    public Boolean saveIfAbsent(RefreshTokenUseEntity entity);

    public Boolean delete(UUID id);
}
