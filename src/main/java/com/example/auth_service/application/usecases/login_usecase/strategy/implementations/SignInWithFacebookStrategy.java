package com.example.auth_service.application.usecases.login_usecase.strategy.implementations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.commands.IAccountARCommandRepo;
import com.example.auth_service.application.interface_services.facebook.FBTokenPayload;
import com.example.auth_service.application.interface_services.facebook.IFacebookService;
import com.example.auth_service.application.ports.inputs.login_inputs.extensions.SignInWithFacebookInput;
import com.example.auth_service.application.ports.outputs.TokenOutput;
import com.example.auth_service.application.usecases.CreateProfileUsecase;
import com.example.auth_service.application.usecases.CreateTokenUseCase;
import com.example.auth_service.application.usecases.login_usecase.strategy.interfaces.SignInStrategy;
import com.example.auth_service.core.respones.exceptions.specific_case.OIDCInvalidException;
import com.example.auth_service.domain.aggregate_roots.AccountAR;
import com.example.auth_service.domain.aggregate_roots.ProfileAR;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.oauth2.FacebookAuthProvider;
import com.example.auth_service.domain.types.RoleType;
import com.example.auth_service.domain.value_objects.VOEmail;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SignInWithFacebookStrategy implements SignInStrategy<SignInWithFacebookInput> {

    private final IAccountARCommandRepo accountCommandRepositoty;
    private final IFacebookService facebookService;
    private final CreateTokenUseCase createTokenUseCase;
    private final CreateProfileUsecase createProfileUsecase;

    @Override
    public TokenOutput login(SignInWithFacebookInput loginInput) {
        boolean isVerifier = facebookService.verifyOIDC(loginInput.getOidcID(), loginInput.getRawNonce());

        if (!isVerifier) {
            throw new OIDCInvalidException();
        }

        FBTokenPayload payload = facebookService.getInfoAccountFB(loginInput.getOidcID());

        VOEmail email = VOEmail.create(payload.getEmail());

        AccountAR arAccount = accountCommandRepositoty.findAccountARByEmail(email)
                .orElseGet(
                        () -> {
                            FacebookAuthProvider authProvider = new FacebookAuthProvider(payload.getSub());
                            AccountAR newAccount = AccountAR.create(email.getValue(), RoleType.USER, authProvider);
                            accountCommandRepositoty.save(newAccount);
                            return newAccount;
                        });

        String usernameForEmail = email.getValue().substring(0, email.getValue().indexOf("@"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthdayLocalDate = LocalDate.parse(payload.getUser_birthday(), formatter);

        ProfileAR newProfile = ProfileAR.create(usernameForEmail, payload.getName(), payload.getPicture(),
                birthdayLocalDate, arAccount.getId());

        createProfileUsecase.excute(newProfile);

        return createTokenUseCase.excute(arAccount.getId(), arAccount.getRole());

    }

}
