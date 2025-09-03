package com.example.auth_service.interface_adapter.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.auth_service.domain.aggregate_roots.ARAccount;
import com.example.auth_service.domain.entities.entity_auth_provider.abstraction.EAuthProvider;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.UserPassAuthProvider;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.oauth2.extensions.FacebookAuthProvider;
import com.example.auth_service.infrastructure.persistences.repositories.projection_interface.IARAccountProjection;

public class ARAccountMapper {
    public static Optional<ARAccount> toARAccount(List<IARAccountProjection> rows) {
        if (rows.isEmpty() || rows == null) return Optional.empty();
        IARAccountProjection fist = rows.getFirst();
        List<EAuthProvider> authProviders = new ArrayList<>();
        for (IARAccountProjection row : rows) {
            if (row.getProviderId() == null)
                continue;
            switch (row.getProviderType()) {
                case "USER_PASS_TYPE":
                    authProviders.add(new UserPassAuthProvider(row.getProviderId(), row.getPasswordHashed()));
                    break;
                case "FACEBOOK_TYPE":
                    authProviders.add(new FacebookAuthProvider(row.getProviderId(), row.getFacebookAccountId()));
                    break;
            }
        }
        ARAccount arAccount = ARAccount.toAggregate(fist.getAccountId(), fist.getEmail(), authProviders);
        return Optional.of(arAccount);
    }
}
