package com.example.auth_service.application.ports.inputs.login_inputs.extensions;

import com.example.auth_service.application.ports.inputs.login_inputs.abstraction.ASignInInput;
import com.example.auth_service.domain.types.ProviderType;

public class SignInWithFacebookInput extends ASignInInput{
    public SignInWithFacebookInput(){
        super(ProviderType.FACEBOOK_TYPE);
    }
}
