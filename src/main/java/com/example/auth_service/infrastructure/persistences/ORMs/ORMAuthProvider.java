package com.example.auth_service.infrastructure.persistences.ORMs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.auth_service.domain.types.ProviderType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth_providers")
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class ORMAuthProvider extends ORMBase{
    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private ORMAccount account;

    @Column(name = "provider_type")
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @OneToOne(mappedBy = "authProvider", cascade = CascadeType.ALL, orphanRemoval = true)
    private ORMAuthUserPass authUserPass;

    @OneToMany(mappedBy = "authProvider", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ORMAuthOAuth2> authOAuth2s = new ArrayList<>();
}
