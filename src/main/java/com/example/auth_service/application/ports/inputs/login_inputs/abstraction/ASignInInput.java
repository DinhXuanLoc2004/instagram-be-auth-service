package com.example.auth_service.application.ports.inputs.login_inputs.abstraction;

import com.example.auth_service.domain.types.ProviderType;

public abstract class ASignInInput {
    protected ProviderType providerType;

    protected ASignInInput(ProviderType providerType){
        this.providerType = providerType;
    }

    public ProviderType getProviderType(){
        return this.providerType;
    }
}
