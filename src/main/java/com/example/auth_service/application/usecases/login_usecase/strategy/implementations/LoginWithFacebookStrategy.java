package com.example.auth_service.application.usecases.login_usecase.strategy.implementations;

import com.example.auth_service.application.ports.inputs.login_inputs.extensions.LoginWithFacebookInput;
import com.example.auth_service.application.ports.outputs.LoginOutput;
import com.example.auth_service.application.usecases.login_usecase.strategy.interfaces.LoginStrategy;

public class LoginWithFacebookStrategy implements LoginStrategy<LoginWithFacebookInput>{

    @Override
    public LoginOutput login(LoginWithFacebookInput loginInput) {
        return new LoginOutput(null, null);
    }
    
}
