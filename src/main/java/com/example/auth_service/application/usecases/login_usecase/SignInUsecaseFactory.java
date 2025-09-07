package com.example.auth_service.application.usecases.login_usecase;

import java.util.Map;

import com.example.auth_service.application.ports.inputs.login_inputs.abstraction.ASignInInput;
import com.example.auth_service.application.usecases.login_usecase.strategy.interfaces.SignInStrategy;
import com.example.auth_service.domain.types.ProviderType;

public class SignInUsecaseFactory {
    private final Map<ProviderType, SignInStrategy<? extends ASignInInput>> strategies;

    public SignInUsecaseFactory(Map<ProviderType, SignInStrategy<? extends ASignInInput>> strategies) {
        this.strategies = strategies;
    }

    @SuppressWarnings("unchecked")
    public <T extends ASignInInput> SignInUsecase<T> getUsecase(ProviderType providerType) {
        SignInStrategy<T> strategy = (SignInStrategy<T>) strategies.get(providerType);
        if (strategy == null)
            throw new IllegalArgumentException("Unsupported provider type: " + providerType);
        return new SignInUsecase<>(strategy);
    }
}
