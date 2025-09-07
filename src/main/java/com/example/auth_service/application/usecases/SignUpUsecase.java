package com.example.auth_service.application.usecases;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.commands.IARAccountCommandRepositoty;
import com.example.auth_service.application.interface_services.IPasswordService;
import com.example.auth_service.application.interface_services.ITokenService;
import com.example.auth_service.application.ports.inputs.SignUpInput;
import com.example.auth_service.application.ports.outputs.JWTOutput;
import com.example.auth_service.core.exceptions.specific_case.EmailAlreadyExistsException;
import com.example.auth_service.domain.aggregate_roots.ARAccount;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.UserPassAuthProvider;
import com.example.auth_service.domain.value_objects.VOEmail;

@Component
public class SignUpUsecase {
    private final IARAccountCommandRepositoty commandRepositoty;
    private final IPasswordService passwordService;
    private final ITokenService tokenService;

    public SignUpUsecase(IARAccountCommandRepositoty commandRepositoty,
            IPasswordService passwordService, ITokenService tokenService) {
        this.commandRepositoty = commandRepositoty;
        this.passwordService = passwordService;
        this.tokenService = tokenService;
    }

    public JWTOutput execute(SignUpInput input) {
        VOEmail voEmail = VOEmail.create(input.getEmail());

        boolean existsEmail = commandRepositoty.existsEmail(voEmail);

        if (existsEmail)
            throw new EmailAlreadyExistsException();

        String passwordHashed = passwordService.hash(input.getPassword());

        UserPassAuthProvider userPassAuthProvider = new UserPassAuthProvider(passwordHashed);

        ARAccount account = ARAccount.create(input.getEmail(), userPassAuthProvider);

        commandRepositoty.save(account);

        JWTOutput jwt = tokenService.generateToken(account.getId().toString());

        return jwt;

    }
}
