package com.example.auth_service.application.ports.outputs;

public class LoginOutput {
    private final String accessToken;
    private final String refreshToken;

    public LoginOutput(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken(){
        return accessToken;
    }

    public String refreshToken(){
        return refreshToken;
    }
}
