package com.example.auth_service.application.usecases.login_usecase.strategy.implementations;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.commands.IARAccountCommandRepositoty;
import com.example.auth_service.application.interface_services.IPasswordService;
import com.example.auth_service.application.interface_services.ITokenService;
import com.example.auth_service.application.ports.inputs.login_inputs.extensions.SignInWithUserPassInput;
import com.example.auth_service.application.ports.outputs.JWTOutput;
import com.example.auth_service.application.usecases.login_usecase.strategy.interfaces.SignInStrategy;
import com.example.auth_service.core.respones.exceptions.specific_case.EmailNotFoundException;
import com.example.auth_service.core.respones.exceptions.specific_case.PasswordIncorrectException;
import com.example.auth_service.domain.aggregate_roots.ARAccount;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.UserPassAuthProvider;
import com.example.auth_service.domain.types.ProviderType;
import com.example.auth_service.domain.value_objects.VOEmail;

@Component
public class SignInWithUserPassStrategy implements SignInStrategy<SignInWithUserPassInput> {

    private final IARAccountCommandRepositoty accountCommandRepositoty;
    private final ITokenService tokenService;
    private final IPasswordService passwordService;

    public SignInWithUserPassStrategy(IARAccountCommandRepositoty accountCommandRepositoty,
            ITokenService tokenService, IPasswordService passwordService) {
        this.accountCommandRepositoty = accountCommandRepositoty;
        this.tokenService = tokenService;
        this.passwordService = passwordService;
    }

    @Override
    public JWTOutput login(SignInWithUserPassInput loginInput) {
        VOEmail voEmail = VOEmail.create(loginInput.getEmail());

        ARAccount account = accountCommandRepositoty.findARAccountByEmail(voEmail)
                .orElseThrow(() -> new EmailNotFoundException());

        UserPassAuthProvider userPassAuthProvider = account.getAuthProvider(ProviderType.USER_PASS_TYPE,
                UserPassAuthProvider.class);

        if (!passwordService.verify(loginInput.getPassword(), userPassAuthProvider.getPasswordHashed())) {
            throw new PasswordIncorrectException();
        }

        return tokenService.generateToken(account.getId().toString());
    }

}
