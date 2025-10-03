package com.example.auth_service.application.interface_repositories.commands;

import java.util.UUID;

import com.example.auth_service.domain.entities.RefreshTokenUseEntity;

public interface IRefreshTokenUsingCommandRepository extends ICommandBase<RefreshTokenUseEntity, UUID> {

}
