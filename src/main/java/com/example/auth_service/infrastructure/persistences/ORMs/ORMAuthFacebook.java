package com.example.auth_service.infrastructure.persistences.ORMs;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth_facebook")
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class ORMAuthFacebook extends ORMBase {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "auth_provider_id", nullable = false, unique = true)
    private UUID authProviderId;

    @Column(name = "facebook_account_id")
    private String facebookAccountId;

    @Column(name = "access_token_hashed")
    private String accessTokenHashed;

    @Column(name = "refresh_token_hashed")
    private String refreshTokenHashed;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Column(name = "name_provider_user")
    private String nameProviderUser;

    @Column(name = "avatar_provider_user")
    private String avatarProviderUser;

}
