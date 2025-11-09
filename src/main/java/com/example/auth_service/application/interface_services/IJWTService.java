package com.example.auth_service.application.interface_services;

import java.time.Instant;
import java.util.UUID;

import com.example.auth_service.domain.types.RoleType;

public interface IJWTService {
    public String signJWT(UUID accountId, RoleType role, Instant issuedAt, Instant expireAt);
}
