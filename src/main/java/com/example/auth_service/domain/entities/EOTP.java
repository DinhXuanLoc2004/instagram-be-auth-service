package com.example.auth_service.domain.entities;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class EOTP {
    private final UUID userId;
    private final String codeHashed;
    private final Instant expiredAt;

    private EOTP(UUID userId, String OTPHashed, Instant expiredAt) {
        this.userId = userId;
        this.codeHashed = OTPHashed;
        this.expiredAt = expiredAt;
    }

    public static EOTP create(UUID userId, String codeHashed) {
        Instant expiredAt = Instant.now().plus(Duration.ofMinutes(5));
        return new EOTP(userId, codeHashed, expiredAt);
    }

    public UUID getUserId() {
        return userId;
    }

    public String getCodeHashed() {
        return codeHashed;
    }

    public Instant getExpiredAt() {
        return expiredAt;
    }

}
