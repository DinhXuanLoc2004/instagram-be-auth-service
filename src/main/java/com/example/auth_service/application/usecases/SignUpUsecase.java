package com.example.auth_service.application.usecases;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.commands.IAccountARCommandRepo;
import com.example.auth_service.application.interface_services.IPasswordService;
import com.example.auth_service.application.ports.inputs.SignUpInput;
import com.example.auth_service.application.ports.outputs.TokenOutput;
import com.example.auth_service.core.respones.exceptions.specific_case.EmailAlreadyExistsException;
import com.example.auth_service.domain.aggregate_roots.AccountAR;
import com.example.auth_service.domain.aggregate_roots.ProfileAR;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.UserPassAuthProvider;
import com.example.auth_service.domain.types.RoleType;
import com.example.auth_service.domain.value_objects.VOEmail;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SignUpUsecase {
    private final IAccountARCommandRepo commandRepositoty;
    private final IPasswordService passwordService;
    private final CreateTokenUseCase createTokenUseCase;
    private final CreateProfileUsecase createProfileUsecase;

    public TokenOutput execute(SignUpInput input) {
        VOEmail voEmail = VOEmail.create(input.getEmail());

        boolean existsEmail = commandRepositoty.existsEmail(voEmail);

        if (existsEmail)
            throw new EmailAlreadyExistsException();

        String passwordHashed = passwordService.hash(input.getPassword());

        UserPassAuthProvider userPassAuthProvider = new UserPassAuthProvider(passwordHashed);

        AccountAR account = AccountAR.create(input.getEmail(), RoleType.USER, userPassAuthProvider);

        commandRepositoty.save(account);

        ProfileAR profileAR = ProfileAR.create(voEmail.getValue(), account.getId());
        createProfileUsecase.excute(profileAR);

        return createTokenUseCase.excute(account.getId(), account.getRole());
    }
}
