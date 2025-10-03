package com.example.auth_service.application.usecases;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.commands.IARAccountCommandRepositoty;
import com.example.auth_service.application.interface_repositories.commands.IRefreshTokenUsingCommandRepository;
import com.example.auth_service.application.interface_services.IJWTService;
import com.example.auth_service.application.interface_services.IPasswordService;
import com.example.auth_service.application.ports.inputs.SignUpInput;
import com.example.auth_service.application.ports.outputs.TokenOutput;
import com.example.auth_service.core.respones.exceptions.specific_case.EmailAlreadyExistsException;
import com.example.auth_service.domain.aggregate_roots.ARAccount;
import com.example.auth_service.domain.entities.RefreshTokenUseEntity;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.UserPassAuthProvider;
import com.example.auth_service.domain.types.RoleType;
import com.example.auth_service.domain.value_objects.VOEmail;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SignUpUsecase {
    private final IARAccountCommandRepositoty commandRepositoty;
    private final IPasswordService passwordService;
    private final IJWTService jwtService;
    private final IRefreshTokenUsingCommandRepository refreshTokenUsedCommandRepository;

    public TokenOutput execute(SignUpInput input) {
        VOEmail voEmail = VOEmail.create(input.getEmail());

        boolean existsEmail = commandRepositoty.existsEmail(voEmail);

        if (existsEmail)
            throw new EmailAlreadyExistsException();

        String passwordHashed = passwordService.hash(input.getPassword());

        UserPassAuthProvider userPassAuthProvider = new UserPassAuthProvider(passwordHashed);

        ARAccount account = ARAccount.create(input.getEmail(), RoleType.USER, userPassAuthProvider);

        commandRepositoty.save(account);

        final String accessToken = jwtService.signJWT(account.getId(), account.getRole());
        final RefreshTokenUseEntity refreshTokenUseEntity = RefreshTokenUseEntity.create(account.getId());
        refreshTokenUsedCommandRepository.save(refreshTokenUseEntity);

        return new TokenOutput(accessToken, refreshTokenUseEntity.getId().toString());

    }
}
