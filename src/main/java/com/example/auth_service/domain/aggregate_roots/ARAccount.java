package com.example.auth_service.domain.aggregate_roots;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.auth_service.core.exceptions.specific_case.AuthProviderNotExistException;
import com.example.auth_service.domain.entities.entity_auth_provider.abstraction.EAuthProvider;
import com.example.auth_service.domain.types.ProviderType;
import com.example.auth_service.domain.value_objects.VOEmail;

public class ARAccount {
    private final UUID id;
    private VOEmail email;
    private List<EAuthProvider> authProviders = new ArrayList<>();

    private ARAccount(VOEmail email, EAuthProvider authProvider) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.addAuthProvider(authProvider);
    }

    public static ARAccount create(String email, EAuthProvider AuthProvider) {
        VOEmail voEmail = VOEmail.create(email);
        return new ARAccount(voEmail, AuthProvider);
    }

    public <T extends EAuthProvider> T getAuthProvider(ProviderType providerType, Class<T> clazz) {
        return authProviders.stream()
                .filter(p -> p.getProviderType() == providerType)
                .map(clazz::cast)
                .findFirst()
                .orElseThrow(
                        () -> new AuthProviderNotExistException(
                                "Provider" + providerType + " not exist with account!"));
    }

    public void addAuthProvider(EAuthProvider eAuthProvider) {
        boolean exists = authProviders.stream()
                .anyMatch(p -> p.getProviderType().equals(eAuthProvider.getProviderType()));
        if (exists) {
            throw new IllegalArgumentException("Provider already linked for this account");
        }
        this.authProviders.add(eAuthProvider);
    }

    public void removeAuthProvider(UUID idAuthProvider) {

    }

    public void changeEmail(String newEmail) {
        this.email = VOEmail.create(newEmail);
    }

    public UUID getId() {
        return this.id;
    }

    public VOEmail getEmail() {
        return this.email;
    }

    public List<EAuthProvider> getAuthProviders() {
        return List.copyOf(this.authProviders);
    }
}
