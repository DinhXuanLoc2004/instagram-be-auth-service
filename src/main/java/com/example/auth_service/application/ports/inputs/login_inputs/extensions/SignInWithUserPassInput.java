package com.example.auth_service.application.ports.inputs.login_inputs.extensions;

import com.example.auth_service.application.ports.inputs.login_inputs.abstraction.ASignInInput;
import com.example.auth_service.domain.types.ProviderType;

public class SignInWithUserPassInput extends ASignInInput{
    private final String email;
    private final String password;

    public SignInWithUserPassInput(String email, String password){
        super(ProviderType.USER_PASS_TYPE);
        this.email = email;
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }
}
