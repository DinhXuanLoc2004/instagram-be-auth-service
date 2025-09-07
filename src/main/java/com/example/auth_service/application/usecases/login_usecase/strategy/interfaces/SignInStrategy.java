package com.example.auth_service.application.usecases.login_usecase.strategy.interfaces;

import com.example.auth_service.application.ports.inputs.login_inputs.abstraction.ASignInInput;
import com.example.auth_service.application.ports.outputs.JWTOutput;

public interface SignInStrategy<T extends ASignInInput> {
    public JWTOutput login(T loginInput);
}
