package com.example.auth_service.application.ports.outputs;

public class TokenOutput {
    private final String accessToken;
    private final String refreshToken;

    public TokenOutput(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
