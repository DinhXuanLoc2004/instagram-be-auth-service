package com.example.auth_service.application.usecases.login_usecase.strategy.interfaces;

import com.example.auth_service.application.ports.inputs.login_inputs.abstraction.ASignInInput;
import com.example.auth_service.application.ports.outputs.TokenOutput;

public interface SignInStrategy<T extends ASignInInput> {
    public TokenOutput login(T loginInput);
}
