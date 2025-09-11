package com.example.auth_service.core.respones.exceptions.specific_case;

import com.example.auth_service.core.respones.exceptions.base.extendtions.BadRequestException;

public class AuthProviderNotExistException extends BadRequestException{

    public AuthProviderNotExistException(){
        super("Authentication provider not exist");
    }

    public AuthProviderNotExistException(String message) {
        super(message);
    }
    
}
