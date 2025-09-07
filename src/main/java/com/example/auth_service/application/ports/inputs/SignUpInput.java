package com.example.auth_service.application.ports.inputs;

public class SignUpInput {
    private final String email;
    private final String password;

    
    public SignUpInput(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

}
