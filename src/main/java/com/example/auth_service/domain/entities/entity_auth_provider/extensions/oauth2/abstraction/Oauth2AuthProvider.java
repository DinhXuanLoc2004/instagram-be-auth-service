package com.example.auth_service.domain.entities.entity_auth_provider.extensions.oauth2.abstraction;

import com.example.auth_service.domain.entities.entity_auth_provider.abstraction.EAuthProvider;
import com.example.auth_service.domain.types.ProviderType;

public abstract class Oauth2AuthProvider extends EAuthProvider{

    private final String providerUserId;

    protected Oauth2AuthProvider(ProviderType providerType, String providerUserId) {
        super(providerType);
        this.providerUserId = providerUserId;
    }

    public String getProviderUserId(){
        return this.providerUserId;
    }
    
}
