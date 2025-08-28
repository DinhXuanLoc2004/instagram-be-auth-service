package com.example.auth_service.application.ports.inputs;

import java.util.UUID;

public class VerifyAccountInput {
    private final UUID accountId;
    private final String code;
    
    public VerifyAccountInput(UUID userId, String code) {
        this.accountId = userId;
        this.code = code;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getCode() {
        return code;
    }
}
