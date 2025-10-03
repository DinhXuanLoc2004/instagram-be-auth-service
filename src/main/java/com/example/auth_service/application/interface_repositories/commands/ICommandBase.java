package com.example.auth_service.application.interface_repositories.commands;

public interface ICommandBase<T, ID> {
    public void save(T data);

    public void delete(ID id);
}
