package com.example.auth_service.domain.entities;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class RefreshTokenUseEntity {
    private final UUID id;
    private final UUID accountId;
    private final Instant expiredAt;
    private final Duration expireDuration;


    private RefreshTokenUseEntity(UUID id, UUID accountId){
        this.id = id;
        this.accountId = accountId;
        this.expireDuration = Duration.ofDays(1);
        this.expiredAt = Instant.now().plus(expireDuration);
    }

    public static RefreshTokenUseEntity create(UUID accountId){
        return new RefreshTokenUseEntity(UUID.randomUUID(), accountId);
    }

    public UUID getId(){
        return this.id;
    }

    public UUID getAccountId(){
        return this.accountId;
    }

    public Instant getExpireAt(){
        return this.expiredAt;
    }

    public Duration getExpireDuration(){
        return this.expireDuration;
    }
}
