package com.example.auth_service.application.interface_repositories.commands;

import java.util.UUID;

import com.example.auth_service.domain.aggregate_roots.ARAccount;
import com.example.auth_service.domain.value_objects.VOEmail;

public interface IARAccountCommandRepositoty extends ICommandBase<ARAccount, UUID>{
    ARAccount findARAccontById(UUID id);

    ARAccount findARAccountByEmail(VOEmail email);

    boolean existsEmail(VOEmail email);
}
