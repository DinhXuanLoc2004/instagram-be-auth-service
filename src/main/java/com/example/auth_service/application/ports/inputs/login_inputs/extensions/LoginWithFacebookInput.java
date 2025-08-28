package com.example.auth_service.application.ports.inputs.login_inputs.extensions;

import com.example.auth_service.application.ports.inputs.login_inputs.abstraction.ALoginInput;
import com.example.auth_service.domain.types.ProviderType;

public class LoginWithFacebookInput extends ALoginInput{
    public LoginWithFacebookInput(){
        super(ProviderType.FACEBOOK_TYPE);
    }
}
