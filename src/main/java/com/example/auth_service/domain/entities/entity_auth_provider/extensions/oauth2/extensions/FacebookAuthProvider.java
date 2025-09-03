package com.example.auth_service.domain.entities.entity_auth_provider.extensions.oauth2.extensions;

import java.util.UUID;

import com.example.auth_service.domain.entities.entity_auth_provider.extensions.oauth2.abstraction.Oauth2AuthProvider;
import com.example.auth_service.domain.types.ProviderType;

public class FacebookAuthProvider extends Oauth2AuthProvider{

    public FacebookAuthProvider(String providerUserId) {
        super(ProviderType.FACEBOOK_TYPE, providerUserId);
    }

    public FacebookAuthProvider(UUID id, String providerUserId){
        super(id, ProviderType.FACEBOOK_TYPE, providerUserId);
    }
    
}
