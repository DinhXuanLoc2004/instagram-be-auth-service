package com.example.auth_service.application.interface_repositories.queries.views;

import java.util.UUID;

import com.example.auth_service.domain.types.RoleType;

public class AccountRoleView {
    private final UUID id;
    private final RoleType role;

    public AccountRoleView(UUID id, RoleType role) {
        this.id = id;
        this.role = role;
    }

    public UUID getId(){
        return this.id;
    }

    public RoleType getRole(){
        return this.role;
    }
}
