package com.example.auth_service.infrastructure.persistences.repositories.projections.interfaces;

public interface IAccountRoleProjection {
    public byte[] getAccountId();
    public String getRole();
}
