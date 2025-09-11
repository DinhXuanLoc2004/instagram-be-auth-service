package com.example.auth_service.domain.aggregate_roots;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.auth_service.core.respones.exceptions.specific_case.AlreadyVerifiedException;
import com.example.auth_service.core.respones.exceptions.specific_case.AuthProviderNotExistException;
import com.example.auth_service.core.respones.exceptions.specific_case.ProviderAlreadyException;
import com.example.auth_service.domain.entities.entity_auth_provider.abstraction.EAuthProvider;
import com.example.auth_service.domain.types.ProviderType;
import com.example.auth_service.domain.value_objects.VOEmail;

public class ARAccount {
    private final UUID id;
    private VOEmail email;
    private boolean isVerified;
    private List<EAuthProvider> authProviders = new ArrayList<>();

    public void verify() {
        if (this.isVerified == true)
            throw new AlreadyVerifiedException();
        this.isVerified = true;
    }

    private ARAccount(VOEmail email, EAuthProvider authProvider) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.addAuthProvider(authProvider);
    }

    private ARAccount(UUID id, String email, boolean isVerified, List<EAuthProvider> authProviders) {
        this.id = id;
        this.email = VOEmail.create(email);
        this.isVerified = isVerified;
        this.authProviders = authProviders;
    }

    public static ARAccount toAggregate(UUID id, String email, boolean isVerified, List<EAuthProvider> authProviders) {
        return new ARAccount(id, email, isVerified, authProviders);
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
            throw new ProviderAlreadyException("Provider already linked for this account");
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

    public boolean getIsVerified() {
        return isVerified;
    }
}
