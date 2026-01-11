package com.example.auth_service.application.usecases.login_usecase.strategy.implementations;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.commands.IARAccountCommandRepositoty;
import com.example.auth_service.application.interface_services.facebook.FBTokenPayload;
import com.example.auth_service.application.interface_services.facebook.IFacebookService;
import com.example.auth_service.application.ports.inputs.login_inputs.extensions.SignInWithFacebookInput;
import com.example.auth_service.application.ports.outputs.TokenOutput;
import com.example.auth_service.application.usecases.CreateTokenUseCase;
import com.example.auth_service.application.usecases.login_usecase.strategy.interfaces.SignInStrategy;
import com.example.auth_service.core.respones.exceptions.specific_case.OIDCInvalidException;
import com.example.auth_service.domain.aggregate_roots.ARAccount;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.oauth2.FacebookAuthProvider;
import com.example.auth_service.domain.types.RoleType;
import com.example.auth_service.domain.value_objects.VOEmail;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SignInWithFacebookStrategy implements SignInStrategy<SignInWithFacebookInput> {

    private final IARAccountCommandRepositoty accountCommandRepositoty;
    private final IFacebookService facebookService;
    private final CreateTokenUseCase createTokenUseCase;

    @Override
    public TokenOutput login(SignInWithFacebookInput loginInput) {
        boolean isVerifier = facebookService.verifyOIDC(loginInput.getOidcID(), loginInput.getRawNonce());

        if (!isVerifier) {
            throw new OIDCInvalidException();
        }

        FBTokenPayload payload = facebookService.getInfoAccountFB(loginInput.getOidcID());

        VOEmail email = VOEmail.create(payload.getEmail());

        ARAccount arAccount = accountCommandRepositoty.findARAccountByEmail(email)
                .orElseGet(
                        () -> {
                            FacebookAuthProvider authProvider = new FacebookAuthProvider(payload.getSub());
                            ARAccount newAccount = ARAccount.create(email.getValue(), RoleType.USER, authProvider);
                            accountCommandRepositoty.save(newAccount);
                            return newAccount;
                        });

        return createTokenUseCase.excute(arAccount.getId(), arAccount.getRole());

    }

}
