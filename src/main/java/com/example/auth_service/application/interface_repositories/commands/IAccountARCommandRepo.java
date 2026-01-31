package com.example.auth_service.application.interface_repositories.commands;

import java.util.Optional;
import java.util.UUID;

import com.example.auth_service.domain.aggregate_roots.AccountAR;
import com.example.auth_service.domain.value_objects.VOEmail;

public interface IAccountARCommandRepo extends ICommandBase<AccountAR, UUID>{
    Optional<AccountAR> findAccountARById(UUID id);

    Optional<AccountAR> findAccountARByEmail(VOEmail email);

    boolean existsEmail(VOEmail email);
}
