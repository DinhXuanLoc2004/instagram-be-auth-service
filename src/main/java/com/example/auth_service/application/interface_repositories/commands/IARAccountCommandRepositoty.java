package com.example.auth_service.application.interface_repositories.commands;

import java.util.Optional;
import java.util.UUID;

import com.example.auth_service.domain.aggregate_roots.ARAccount;
import com.example.auth_service.domain.value_objects.VOEmail;

public interface IARAccountCommandRepositoty extends ICommandBase<ARAccount, UUID>{
    Optional<ARAccount> findARAccontById(UUID id);

    Optional<ARAccount> findARAccountByEmail(VOEmail email);

    boolean existsEmail(VOEmail email);
}
