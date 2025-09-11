package com.example.auth_service.application.usecases.login_usecase;

import com.example.auth_service.application.ports.inputs.login_inputs.abstraction.ASignInInput;
import com.example.auth_service.application.ports.outputs.JWTOutput;
import com.example.auth_service.application.usecases.login_usecase.strategy.interfaces.SignInStrategy;

public class SignInUsecase<T extends ASignInInput> {
    private final SignInStrategy<T> signInStrategy;

    public SignInUsecase(SignInStrategy<T> loginStrategy){
        this.signInStrategy = loginStrategy;
    }

    public JWTOutput execute(T loginInput){
        return signInStrategy.login(loginInput);
    }
}
