package com.example.auth_service.application.usecases.login_usecase.strategy.implementations;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.commands.IARAccountCommandRepositoty;
import com.example.auth_service.application.interface_repositories.commands.IRefreshTokenUsingCommandRepository;
import com.example.auth_service.application.interface_services.IPasswordService;
import com.example.auth_service.application.interface_services.IJWTService;
import com.example.auth_service.application.ports.inputs.login_inputs.extensions.SignInWithUserPassInput;
import com.example.auth_service.application.ports.outputs.TokenOutput;
import com.example.auth_service.application.usecases.login_usecase.strategy.interfaces.SignInStrategy;
import com.example.auth_service.core.respones.exceptions.specific_case.EmailNotFoundException;
import com.example.auth_service.core.respones.exceptions.specific_case.PasswordIncorrectException;
import com.example.auth_service.domain.aggregate_roots.ARAccount;
import com.example.auth_service.domain.entities.RefreshTokenUseEntity;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.UserPassAuthProvider;
import com.example.auth_service.domain.types.ProviderType;
import com.example.auth_service.domain.value_objects.VOEmail;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SignInWithUserPassStrategy implements SignInStrategy<SignInWithUserPassInput> {

    private final IARAccountCommandRepositoty accountCommandRepositoty;
    private final IPasswordService passwordService;
    private final IJWTService jwtService;
    private final IRefreshTokenUsingCommandRepository refreshTokenUsingCommandRepository;

    @Override
    public TokenOutput login(SignInWithUserPassInput loginInput) {
        VOEmail voEmail = VOEmail.create(loginInput.getEmail());

        ARAccount account = accountCommandRepositoty.findARAccountByEmail(voEmail)
                .orElseThrow(() -> new EmailNotFoundException());

        UserPassAuthProvider userPassAuthProvider = account.getAuthProvider(ProviderType.USER_PASS_TYPE,
                UserPassAuthProvider.class);

        if (!passwordService.verify(loginInput.getPassword(), userPassAuthProvider.getPasswordHashed())) {
            throw new PasswordIncorrectException();
        }

        final String accessToken = jwtService.signJWT(account.getId(), account.getRole());
        final RefreshTokenUseEntity refreshTokenUseEntity = RefreshTokenUseEntity.create(account.getId());
        refreshTokenUsingCommandRepository.save(refreshTokenUseEntity);
        
        return new TokenOutput(accessToken, refreshTokenUseEntity.getId().toString());
    }

}
