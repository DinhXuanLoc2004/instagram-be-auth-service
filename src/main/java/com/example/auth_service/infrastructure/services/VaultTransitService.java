package com.example.auth_service.infrastructure.services;

import java.time.Instant;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.core.VaultTransitOperations;
import org.springframework.vault.support.Plaintext;

import com.example.auth_service.infrastructure.DTOs.HeaderJWT;
import com.example.auth_service.infrastructure.DTOs.PayloadJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class VaultTransitService {
    private final VaultTransitOperations vaultTransitOperations;
    private final ObjectMapper objectMapper;

    private final String SINATURE_KEY = "signature-key";
    private final String ALG = "RS256";
    private final String TYP = "JWT";

    public VaultTransitService(VaultTemplate vaultTemplate) {
        this.vaultTransitOperations = vaultTemplate.opsForTransit();
        this.objectMapper = new ObjectMapper();
    }

    public String signing(String accountId, String role, Instant issuedAt, Instant expireAt) {

        final HeaderJWT headerJWT = new HeaderJWT(ALG, TYP);
        final PayloadJWT payloadJWT = new PayloadJWT(accountId, role, issuedAt.getEpochSecond(),
                expireAt.getEpochSecond());

        final String headerJson = toJson(headerJWT);
        final String payloadJson = toJson(payloadJWT);

        final String headerBase64Url = base64UrlEncode(headerJson);
        final String payloadBase64Url = base64UrlEncode(payloadJson);

        final String signingInput = headerBase64Url + "." + payloadBase64Url;
        final Plaintext plainText = Plaintext.of(signingInput);

        final String vaultSignature = vaultTransitOperations.sign(SINATURE_KEY, plainText).getSignature();
        final String rawSignature = vaultSignature.substring(vaultSignature.lastIndexOf(":") + 1);

        final String signatureBase64Url = rawToBase64UrlEncode(rawSignature);

        final String accessToken = signingInput + "." + signatureBase64Url;
        return accessToken;
    }

    private String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to convert object to JSON", e);
        }
    }

    private String base64UrlEncode(String value) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(value.getBytes());
    }

    private String rawToBase64UrlEncode(String base64Standard) {
        byte[] decoded = Base64.getDecoder().decode(base64Standard);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(decoded);
    }
}
