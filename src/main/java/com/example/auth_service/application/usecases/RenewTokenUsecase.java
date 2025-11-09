package com.example.auth_service.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.commands.IRefreshTokenUsingCommandRepository;
import com.example.auth_service.application.interface_repositories.queries.IAccountQueryRepository;
import com.example.auth_service.application.interface_repositories.queries.IRefreshTokenUsingQueryReposetory;
import com.example.auth_service.application.interface_repositories.queries.views.AccountRoleView;
import com.example.auth_service.application.ports.outputs.TokenOutput;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RenewTokenUsecase {

    private final IRefreshTokenUsingQueryReposetory refreshTokenUsingQueryReposetory;
    private final IRefreshTokenUsingCommandRepository refreshTokenUsingCommandRepository;
    private final IAccountQueryRepository accountQueryRepository;
    private final CreateTokenUseCase createTokenUseCase;

    public TokenOutput excute(UUID idRefreshToken) {
        final UUID accountId = refreshTokenUsingQueryReposetory.findAccountIdById(idRefreshToken).orElseThrow();

        final AccountRoleView accountRoleView = accountQueryRepository.findById(accountId)
                .orElseThrow();

        refreshTokenUsingCommandRepository.delete(idRefreshToken);

        return createTokenUseCase.excute(accountId, accountRoleView.getRole());
    }
}
