package com.example.auth_service.interface_adapter.implement_services;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_services.IJWTService;
import com.example.auth_service.domain.types.RoleType;
import com.example.auth_service.infrastructure.services.VaultTransitService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ImpJWTService implements IJWTService{

    private final VaultTransitService vaultTransit;
    
    @Override
    public String signJWT(UUID accountId, RoleType role) {
        return vaultTransit.signing(accountId.toString(), role.toString());
    }
    
}
