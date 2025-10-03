package com.example.auth_service.infrastructure.persistences.repositories.projections.interfaces;

public interface IARAccountProjection {
    public byte[] getAccountId();
    public String getEmail();
    public String getRole();
    public boolean getIsVerified();
    public byte[] getAuthProviderId();
    public String getProviderType();
    public String getPasswordHashed();
    public String getFacebookAccountId();
}
