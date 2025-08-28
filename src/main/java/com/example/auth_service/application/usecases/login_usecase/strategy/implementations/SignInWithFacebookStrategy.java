package com.example.auth_service.application.usecases.login_usecase.strategy.implementations;

import com.example.auth_service.application.ports.inputs.login_inputs.extensions.SignInWithFacebookInput;
import com.example.auth_service.application.ports.outputs.SignInOutput;
import com.example.auth_service.application.usecases.login_usecase.strategy.interfaces.SignInStrategy;

public class SignInWithFacebookStrategy implements SignInStrategy<SignInWithFacebookInput>{

    @Override
    public SignInOutput login(SignInWithFacebookInput loginInput) {
        return new SignInOutput(null, null);
    }
    
}
