package com.example.auth_service.application.usecases.login_usecase.strategy.interfaces;

import com.example.auth_service.application.ports.inputs.login_inputs.abstraction.ALoginInput;
import com.example.auth_service.application.ports.outputs.LoginOutput;

public interface LoginStrategy<T extends ALoginInput> {
    public LoginOutput login(T loginInput);
}
