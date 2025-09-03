package com.example.auth_service.infrastructure.persistences.repositories.projection_interface;

import java.util.UUID;

public interface IARAccountProjection {
    public UUID getAccountId();
    public String getEmail();
    public UUID getProviderId();
    public String getProviderType();
    public String getPasswordHashed();
    public String getFacebookAccountId();
}
