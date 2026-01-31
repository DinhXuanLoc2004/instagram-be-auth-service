package com.example.auth_service.interface_adapter.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.auth_service.domain.aggregate_roots.AccountAR;
import com.example.auth_service.domain.entities.entity_auth_provider.abstraction.EAuthProvider;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.UserPassAuthProvider;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.oauth2.FacebookAuthProvider;
import com.example.auth_service.domain.types.RoleType;
import com.example.auth_service.infrastructure.persistences.repositories.projections.interfaces.IARAccountProjection;

public class ARAccountMapper {
    public static Optional<AccountAR> toARAccount(List<IARAccountProjection> rows) {
        if (rows.isEmpty() || rows == null)
            return Optional.empty();
        IARAccountProjection fist = rows.getFirst();
        List<EAuthProvider> authProviders = new ArrayList<>();
        for (IARAccountProjection row : rows) {
            if (row.getAuthProviderId() == null)
                continue;
            switch (row.getProviderType()) {
                case "USER_PASS_TYPE":
                    authProviders.add(new UserPassAuthProvider(UUID.nameUUIDFromBytes(row.getAuthProviderId()),
                            row.getPasswordHashed()));
                    break;
                case "FACEBOOK_TYPE":
                    authProviders.add(new FacebookAuthProvider(UUID.nameUUIDFromBytes(row.getAuthProviderId()),
                            row.getFacebookAccountId()));
                    break;
            }
        }

        final RoleType role = fist.getRole() == RoleType.USER.toString() ? RoleType.USER : RoleType.ADMIN;

        AccountAR arAccount = AccountAR.toAggregate(
                UUID.nameUUIDFromBytes(fist.getAccountId()),
                fist.getEmail(),
                role,
                fist.getIsVerified(),
                authProviders);
        return Optional.of(arAccount);
    }
}
