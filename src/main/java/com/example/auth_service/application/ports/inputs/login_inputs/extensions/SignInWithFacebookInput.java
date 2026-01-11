package com.example.auth_service.application.ports.inputs.login_inputs.extensions;

import com.example.auth_service.application.ports.inputs.login_inputs.abstraction.ASignInInput;
import com.example.auth_service.domain.types.ProviderType;

public class SignInWithFacebookInput extends ASignInInput{
    final String oidcID;
    final String rawNonce;

    public SignInWithFacebookInput(String oidcID, String rawNonce){
        super(ProviderType.FACEBOOK_TYPE);
        this.oidcID = oidcID;
        this.rawNonce = rawNonce;
    }

    public String getOidcID(){
        return this.oidcID;
    }

    public String getRawNonce(){
        return this.rawNonce;
    }
}
