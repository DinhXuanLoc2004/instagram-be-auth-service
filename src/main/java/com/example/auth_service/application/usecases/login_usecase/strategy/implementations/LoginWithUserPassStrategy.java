package com.example.auth_service.application.usecases.login_usecase.strategy.implementations;

import com.example.auth_service.application.interface_repositories.commands.IARAccountCommandRepositoty;
import com.example.auth_service.application.ports.inputs.login_inputs.extensions.LoginWithUserPassInput;
import com.example.auth_service.application.ports.outputs.LoginOutput;
import com.example.auth_service.application.service_for_usecase.IPasswordService;
import com.example.auth_service.application.service_for_usecase.ITokenService;
import com.example.auth_service.application.usecases.login_usecase.strategy.interfaces.LoginStrategy;
import com.example.auth_service.core.exceptions.specific_case.EmailNotFoundException;
import com.example.auth_service.core.exceptions.specific_case.PasswordIncorrectException;
import com.example.auth_service.domain.aggregate_roots.ARAccount;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.UserPassAuthProvider;
import com.example.auth_service.domain.types.ProviderType;
import com.example.auth_service.domain.value_objects.VOEmail;

public class LoginWithUserPassStrategy implements LoginStrategy<LoginWithUserPassInput> {

    private final IARAccountCommandRepositoty accountCommandRepositoty;
    private final ITokenService tokenService;
    private final IPasswordService passwordService;

    public LoginWithUserPassStrategy(IARAccountCommandRepositoty accountCommandRepositoty,
            ITokenService tokenService, IPasswordService passwordService) {
        this.accountCommandRepositoty = accountCommandRepositoty;
        this.tokenService = tokenService;
        this.passwordService = passwordService;
    }

    @Override
    public LoginOutput login(LoginWithUserPassInput loginInput) {
        VOEmail voEmail = VOEmail.create(loginInput.getEmail());

        boolean existsEmail = accountCommandRepositoty.existsEmail(voEmail);

        if (!existsEmail) throw new EmailNotFoundException();

        ARAccount account = accountCommandRepositoty.findARAccountByEmail(voEmail);

        UserPassAuthProvider userPassAuthProvider = account.getAuthProvider(ProviderType.USER_PASS_TYPE,
                UserPassAuthProvider.class);

        if (!passwordService.verify(loginInput.getPassword(), userPassAuthProvider.getPasswordHashed())) {
            throw new PasswordIncorrectException();
        }

        return tokenService.generateToken(account.getId().toString());
    }

}
