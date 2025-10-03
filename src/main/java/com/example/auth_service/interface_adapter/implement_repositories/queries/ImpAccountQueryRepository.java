package com.example.auth_service.interface_adapter.implement_repositories.queries;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.interface_repositories.queries.IAccountQueryRepository;
import com.example.auth_service.application.interface_repositories.queries.views.AccountRoleView;
import com.example.auth_service.domain.types.RoleType;
import com.example.auth_service.infrastructure.persistences.repositories.projections.interfaces.IAccountRoleProjection;
import com.example.auth_service.infrastructure.persistences.repositories.queries.RAccountQuery;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ImpAccountQueryRepository implements IAccountQueryRepository {

    private final RAccountQuery accountQuery;

    @Override
    public Optional<AccountRoleView> findById(UUID id) {
        final Optional<IAccountRoleProjection> accountRoleProjection = accountQuery.findAccountRoleById(id);
        return accountRoleProjection
                .map(projection -> new AccountRoleView(UUID.nameUUIDFromBytes(projection.getAccountId()),
                        RoleType.valueOf(projection.getRole())));
    }

}
