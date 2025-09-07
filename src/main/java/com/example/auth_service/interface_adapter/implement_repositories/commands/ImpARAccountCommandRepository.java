package com.example.auth_service.interface_adapter.implement_repositories.commands;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.auth_service.application.interface_repositories.commands.IARAccountCommandRepositoty;
import com.example.auth_service.domain.aggregate_roots.ARAccount;
import com.example.auth_service.domain.entities.entity_auth_provider.abstraction.EAuthProvider;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.UserPassAuthProvider;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.oauth2.FacebookAuthProvider;
import com.example.auth_service.domain.types.ProviderType;
import com.example.auth_service.domain.value_objects.VOEmail;
import com.example.auth_service.infrastructure.persistences.ORMs.ORMAccount;
import com.example.auth_service.infrastructure.persistences.ORMs.ORMAuthFacebook;
import com.example.auth_service.infrastructure.persistences.ORMs.ORMAuthProvider;
import com.example.auth_service.infrastructure.persistences.ORMs.ORMAuthUserPass;
import com.example.auth_service.infrastructure.persistences.repositories.commands.RAccountCommand;
import com.example.auth_service.infrastructure.persistences.repositories.commands.RAuthFacebookCommand;
import com.example.auth_service.infrastructure.persistences.repositories.commands.RAuthProviderCommand;
import com.example.auth_service.infrastructure.persistences.repositories.commands.RAuthUserPassCommand;
import com.example.auth_service.infrastructure.persistences.repositories.projection_interface.IARAccountProjection;
import com.example.auth_service.infrastructure.persistences.repositories.queries.RAccountQuery;
import com.example.auth_service.interface_adapter.mappers.ARAccountMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ImpARAccountCommandRepository implements IARAccountCommandRepositoty {

    private final RAccountCommand accountCommand;
    private final RAuthProviderCommand authProviderCommand;
    private final RAuthUserPassCommand authUserPassCommand;
    private final RAuthFacebookCommand authFacebookCommand;
    private final RAccountQuery accountQuery;

    @Override
    public void save(ARAccount aggregate) {
        ORMAccount account = new ORMAccount(aggregate.getId(), aggregate.getEmail().getValue(), aggregate.getIsVerified());
        accountCommand.save(account);
        for (EAuthProvider authProvider : aggregate.getAuthProviders()) {
            switch (authProvider.getProviderType()) {
                case USER_PASS_TYPE:
                    ORMAuthProvider ormAuthProvider = new ORMAuthProvider(authProvider.getId(), account.getId(),
                            ProviderType.USER_PASS_TYPE);
                    authProviderCommand.save(ormAuthProvider);

                    ORMAuthUserPass ormAuthUserPass = new ORMAuthUserPass(null, ormAuthProvider.getId(),
                            aggregate.getAuthProvider(ProviderType.USER_PASS_TYPE, UserPassAuthProvider.class)
                                    .getPasswordHashed());
                    authUserPassCommand.save(ormAuthUserPass);
                    break;

                case FACEBOOK_TYPE:
                    ORMAuthProvider ormAuthProviderF = new ORMAuthProvider(authProvider.getId(), account.getId(),
                            ProviderType.FACEBOOK_TYPE);
                    authProviderCommand.save(ormAuthProviderF);

                    FacebookAuthProvider facebookAuthProvider = aggregate.getAuthProvider(ProviderType.FACEBOOK_TYPE,
                            FacebookAuthProvider.class);
                    ORMAuthFacebook ormAuthFacebook = new ORMAuthFacebook(null, ormAuthProviderF.getId(), null,
                            facebookAuthProvider.getFacebookAccountId(), null, null, null, null);
                    authFacebookCommand.save(ormAuthFacebook);
                    break;
            }
        }
    }

    @Override
    public void delete(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<ARAccount> findARAccontById(UUID id) {
        List<IARAccountProjection> accountProjections = accountQuery.findARAccountById(id);
        return ARAccountMapper.toARAccount(accountProjections);
    }

    @Override
    public Optional<ARAccount> findARAccountByEmail(VOEmail email) {
        List<IARAccountProjection> accountProjections = accountQuery.findARAccountByEmail(email.getValue());
        return ARAccountMapper.toARAccount(accountProjections);
    }

    @Override
    public boolean existsEmail(VOEmail email) {
        return accountQuery.existsByEmail(email.getValue());
    }

}
