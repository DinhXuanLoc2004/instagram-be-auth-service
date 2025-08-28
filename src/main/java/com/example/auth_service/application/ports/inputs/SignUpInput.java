package com.example.auth_service.application.ports.inputs;

import com.example.auth_service.application.ports.inputs.login_inputs.extensions.LoginWithUserPassInput;

public class SignUpInput extends LoginWithUserPassInput {

    public SignUpInput(String email, String password) {
        super(email, password);
    }

}
