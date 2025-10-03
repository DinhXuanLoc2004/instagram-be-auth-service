package com.example.auth_service.application.interface_repositories.queries;

import java.util.Optional;
import java.util.UUID;

import com.example.auth_service.application.interface_repositories.queries.views.AccountRoleView;

public interface IAccountQueryRepository {
    public Optional<AccountRoleView> findById(UUID id);
}
