package com.example.auth_service.application.usecases;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.commands.IRefreshTokenUsingCommandRepository;
import com.example.auth_service.application.interface_services.IJWTService;
import com.example.auth_service.application.ports.outputs.TokenOutput;
import com.example.auth_service.core.respones.exceptions.specific_case.RefreshTokenDuplicateException;
import com.example.auth_service.domain.entities.RefreshTokenUseEntity;
import com.example.auth_service.domain.types.RoleType;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CreateTokenUseCase {
    private final IJWTService jwtService;
    private final IRefreshTokenUsingCommandRepository refreshTokenUsingCommandRepository;

    private final Duration expiredDurationForAsseccToken = Duration.ofMinutes(60);

    private final Instant issuedAt() {
        return Instant.now();
    }

    private final Instant expireAt(Duration expiredTime) {
        final Instant expireAt = issuedAt().plus(expiredTime);
        return expireAt;
    }

    public TokenOutput excute(UUID accountId, RoleType role) {
        final Instant expireAtForAccessToken = expireAt(expiredDurationForAsseccToken);
        final String accessToken = jwtService.signJWT(accountId, role, issuedAt(), expireAtForAccessToken);

        final RefreshTokenUseEntity refreshTokenUseEntity = RefreshTokenUseEntity.create(accountId);
        final Boolean saveRefreshToken = refreshTokenUsingCommandRepository.saveIfAbsent(refreshTokenUseEntity);

        if (!saveRefreshToken)
            throw new RefreshTokenDuplicateException();

        final TokenOutput tokenOutput = new TokenOutput(accessToken, refreshTokenUseEntity.getId().toString(),
                expireAtForAccessToken, refreshTokenUseEntity.getExpireAt());

        return tokenOutput;
    }
}
