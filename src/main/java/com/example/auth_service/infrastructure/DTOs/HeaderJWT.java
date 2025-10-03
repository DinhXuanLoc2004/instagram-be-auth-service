package com.example.auth_service.infrastructure.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class HeaderJWT {
    private final String alg;
    private final String typ;
}
