package com.example.auth_service.domain.entities.entity_auth_provider.abstraction;

import java.util.UUID;

import com.example.auth_service.domain.types.ProviderType;

public abstract class EAuthProvider {
    private final UUID id;
    private final ProviderType providerType;

    protected EAuthProvider(ProviderType providerType){
        this.id = UUID.randomUUID();
        this.providerType = providerType;
    }

    public UUID getId(){
        return this.id;
    }

    public ProviderType getProviderType(){
        return this.providerType;
    }
}
