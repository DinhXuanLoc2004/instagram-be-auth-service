package com.example.auth_service.domain.entities.entity_auth_provider.extensions.oauth2;

import java.util.UUID;

import com.example.auth_service.domain.entities.entity_auth_provider.abstraction.EAuthProvider;
import com.example.auth_service.domain.types.ProviderType;

public class FacebookAuthProvider extends EAuthProvider{

    private final String facebookAccountId;

    public FacebookAuthProvider(String facebookAccountId) {
        super(ProviderType.FACEBOOK_TYPE);
        this.facebookAccountId = facebookAccountId;
    }

    public FacebookAuthProvider(UUID id, String facebookAccountId){
        super(id, ProviderType.FACEBOOK_TYPE);
        this.facebookAccountId = facebookAccountId;
    }

    public String getFacebookAccountId(){
        return this.facebookAccountId;
    }
    
}
