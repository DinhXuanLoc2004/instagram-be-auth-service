package com.example.auth_service.application.interface_repositories.queries;

import java.util.List;
import java.util.Optional;

public interface IQueryBase<VIEW, ID> {
    public Optional<VIEW> findById(ID id);

    public List<VIEW> findAll();
}
