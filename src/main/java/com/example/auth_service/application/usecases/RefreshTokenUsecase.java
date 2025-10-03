package com.example.auth_service.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.commands.IRefreshTokenUsingCommandRepository;
import com.example.auth_service.application.interface_repositories.queries.IAccountQueryRepository;
import com.example.auth_service.application.interface_repositories.queries.IRefreshTokenUsingQueryReposetory;
import com.example.auth_service.application.interface_repositories.queries.views.AccountRoleView;
import com.example.auth_service.application.interface_services.IJWTService;
import com.example.auth_service.application.ports.outputs.TokenOutput;
import com.example.auth_service.domain.entities.RefreshTokenUseEntity;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RefreshTokenUsecase {

    private final IRefreshTokenUsingQueryReposetory refreshTokenUsingQueryReposetory;
    private final IRefreshTokenUsingCommandRepository refreshTokenUsingCommandRepository;
    private final IAccountQueryRepository accountQueryRepository;
    private final IJWTService jwtService;

    public TokenOutput excute(UUID idRefreshToken) {
        final UUID accountId = refreshTokenUsingQueryReposetory.findAccountIdById(idRefreshToken).orElseThrow();

        final AccountRoleView accountRoleView = accountQueryRepository.findById(accountId)
                .orElseThrow();

        final String accessToken = jwtService.signJWT(accountRoleView.getId(), accountRoleView.getRole());

        final RefreshTokenUseEntity newRefreshTokenUseEntity = RefreshTokenUseEntity.create(accountRoleView.getId());

        refreshTokenUsingCommandRepository.delete(idRefreshToken);
        refreshTokenUsingCommandRepository.save(newRefreshTokenUseEntity);
        
        final TokenOutput output = new TokenOutput(accessToken, newRefreshTokenUseEntity.getId().toString());

        return output;
    }
}
