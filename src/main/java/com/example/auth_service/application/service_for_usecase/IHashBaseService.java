package com.example.auth_service.application.service_for_usecase;

public interface IHashBaseService{

    public String hash(String raw);

    public boolean verify(String raw, String hashed);

}
