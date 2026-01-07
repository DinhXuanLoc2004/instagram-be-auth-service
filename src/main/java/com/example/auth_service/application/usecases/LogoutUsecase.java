package com.example.auth_service.application.usecases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.commands.IRefreshTokenUsingCommandRepository;
import com.example.auth_service.application.interface_repositories.queries.IRefreshTokenUsingQueryReposetory;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LogoutUsecase {
    private final IRefreshTokenUsingCommandRepository tokenCommandRepo;
    private final IRefreshTokenUsingQueryReposetory tokenQueryRepo;

    public void execute(UUID idRefreshToken){
        final Optional<UUID> accountId = tokenQueryRepo.findAccountIdById(idRefreshToken);

        if (accountId != null) {
            tokenCommandRepo.delete(idRefreshToken);
        }
    }
}
