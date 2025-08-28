package com.example.auth_service.domain.entities.entity_auth_provider.extensions;

import com.example.auth_service.domain.entities.entity_auth_provider.abstraction.EAuthProvider;
import com.example.auth_service.domain.types.ProviderType;

public class UserPassAuthProvider extends EAuthProvider{

    private final String passwordHashed;

    public UserPassAuthProvider(String passwordHashed) {
        super(ProviderType.USER_PASS_TYPE);
        this.passwordHashed = passwordHashed;
    }

    public String getPasswordHashed(){
        return this.passwordHashed;
    }
    
}
