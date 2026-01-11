package com.example.auth_service.application.ports.outputs;

import java.time.Instant;

public class TokenOutput {
    private final String accessToken;
    private final String refreshToken;
    private final Instant accessTokenExpireAt;
    private final long accessTokenExpireAtEpoch;
    private final Instant refreshTokenExpireAt;
    private final long refreshTokenExpireAtEpoch;

    public TokenOutput(String accessToken, String refreshToken, Instant accessTokenExpireAt,
            Instant refreshTokenExpireAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpireAt = accessTokenExpireAt;
        this.refreshTokenExpireAt = refreshTokenExpireAt;
        this.accessTokenExpireAtEpoch = this.accessTokenExpireAt.getEpochSecond();
        this.refreshTokenExpireAtEpoch = this.refreshTokenExpireAt.getEpochSecond();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Instant getAccessTokenExpireAt() {
        return accessTokenExpireAt;
    }

    public long getAccessTokenExpireAtEpoch() {
        return accessTokenExpireAtEpoch;
    }

    public Instant getRefreshTokenExpireAt() {
        return refreshTokenExpireAt;
    }

    public long getRefreshTokenExpireAtEpoch() {
        return refreshTokenExpireAtEpoch;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
