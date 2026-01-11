package com.example.auth_service.application.interface_services.facebook;

public interface IFacebookService {
    public boolean verifyOIDC(String oidcID, String rawNonce);
    public FBTokenPayload getInfoAccountFB(String oidcID);
}
