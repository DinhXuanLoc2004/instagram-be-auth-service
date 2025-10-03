package com.example.auth_service.domain.entities;

import java.util.UUID;

public class RefreshTokenUseEntity {
    private final UUID id;
    private final UUID accountId;
    private final long expired;

    private RefreshTokenUseEntity(UUID id, UUID accountId, long expired){
        this.id = id;
        this.accountId = accountId;
        this.expired = expired;
    }

    public static RefreshTokenUseEntity create(UUID accountId){
        return new RefreshTokenUseEntity(UUID.randomUUID(), accountId, 100);
    }

    public UUID getId(){
        return this.id;
    }

    public UUID getAccountId(){
        return this.accountId;
    }

    public long getExprired(){
        return this.expired;
    }
}
