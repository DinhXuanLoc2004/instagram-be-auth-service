package com.example.auth_service.application.interface_services;

public interface IHashBaseService{

    public String hash(String raw);

    public boolean verify(String raw, String hashed);

}
