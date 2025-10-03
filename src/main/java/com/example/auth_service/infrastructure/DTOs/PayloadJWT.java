package com.example.auth_service.infrastructure.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PayloadJWT {
    @JsonProperty("account_id")
    private final String accountId;
    private final String role;
    private final long exp;
}
