package com.example.auth_service.infrastructure.persistences.repositories.queries;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.auth_service.infrastructure.persistences.ORMs.ORMAccount;
import com.example.auth_service.infrastructure.persistences.repositories.projection_interface.IARAccountProjection;

@Repository
public interface RAccountQuery extends JpaRepository<ORMAccount, UUID> {

    @Query(value = """
            SELECT account.id AS accountId,
                   account.email AS email,
                   provider.id AS providerId,
                   provider.provider_type AS providerType,
                   userpass.password_hashed AS passwordHashed,
                   facebook.facebook_account_id AS facebookAccountId
            FROM accounts account 
            LEFT JOIN auth_providers provider ON account.id = provider.account_id
            LEFT JOIN auth_userpass userpass ON provider.id = userpass.auth_provider_id
            LEFT JOIN auth_facebook facebook ON provider.id = facebook.auth_provider_id
            WHERE account.id = :accountId
            """, nativeQuery = true)
    public List<IARAccountProjection> findARAccountById(@Param(value = "id") UUID accountId);

    @Query(value = """
            SELECT account.id AS accountId,
                   account.email AS email,
                   provider.id AS providerId,
                   provider.provider_type AS providerType,
                   userpass.password_hashed AS passwordHashed,
                   facebook.facebook_account_id AS facebookAccountId
            FROM accounts account
            LEFT JOIN auth_providers provider ON account.id = provider.account_id
            LEFT JOIN auth_userpass userpass ON provider.id = userpass.auth_provider_id
            LEFT JOIN auth_facebook facebook ON provider.id = facebook.auth_provider_id
            WHERE account.email = :email
    """, nativeQuery = true)
    public List<IARAccountProjection> findARAccountByEmail(@Param(value = "email") String email);

    public boolean existsByEmail(@Param(value = "email") String email);
}
