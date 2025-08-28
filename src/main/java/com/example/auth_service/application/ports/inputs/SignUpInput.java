package com.example.auth_service.application.ports.inputs;

import com.example.auth_service.application.ports.inputs.login_inputs.extensions.SignInWithUserPassInput;

public class SignUpInput extends SignInWithUserPassInput {

    public SignUpInput(String email, String password) {
        super(email, password);
    }

}
