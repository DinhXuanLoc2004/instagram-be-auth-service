package com.example.auth_service.application.interface_repositories.queries;

import java.util.Optional;
import java.util.UUID;

public interface IRefreshTokenUsingQueryReposetory {
    public Optional<UUID> findAccountIdById(UUID idRefreshToken);
}
