package com.example.auth_service.application.usecases.login_usecase;

import com.example.auth_service.application.ports.inputs.login_inputs.abstraction.ALoginInput;
import com.example.auth_service.application.ports.outputs.LoginOutput;
import com.example.auth_service.application.usecases.login_usecase.strategy.interfaces.LoginStrategy;

public class SignInUsecase<T extends ALoginInput> {
    private final LoginStrategy<T> loginStrategy;

    public SignInUsecase(LoginStrategy<T> loginStrategy){
        this.loginStrategy = loginStrategy;
    }

    public LoginOutput execute(T loginInput){
        return loginStrategy.login(loginInput);
    }
}
