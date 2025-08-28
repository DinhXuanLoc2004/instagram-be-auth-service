package com.example.auth_service.application.interface_repositories.commands;

public interface ICommandBase<AR, ID> {
    public void save(AR aggregate);

    public void delete(ID id);
}
