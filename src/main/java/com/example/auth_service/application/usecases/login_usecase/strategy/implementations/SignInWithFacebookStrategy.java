package com.example.auth_service.application.usecases.login_usecase.strategy.implementations;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.ports.inputs.login_inputs.extensions.SignInWithFacebookInput;
import com.example.auth_service.application.ports.outputs.TokenOutput;
import com.example.auth_service.application.usecases.login_usecase.strategy.interfaces.SignInStrategy;

@Component
public class SignInWithFacebookStrategy implements SignInStrategy<SignInWithFacebookInput> {

    @Override
    public TokenOutput login(SignInWithFacebookInput loginInput) {
        return new TokenOutput(null, null, null, null);
    }

}
