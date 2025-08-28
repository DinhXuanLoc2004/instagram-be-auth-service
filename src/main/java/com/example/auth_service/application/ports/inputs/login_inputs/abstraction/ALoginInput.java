package com.example.auth_service.application.ports.inputs.login_inputs.abstraction;

import com.example.auth_service.domain.types.ProviderType;

public abstract class ALoginInput {
    protected ProviderType providerType;

    protected ALoginInput(ProviderType providerType){
        this.providerType = providerType;
    }

    public ProviderType getProviderType(){
        return this.providerType;
    }
}
