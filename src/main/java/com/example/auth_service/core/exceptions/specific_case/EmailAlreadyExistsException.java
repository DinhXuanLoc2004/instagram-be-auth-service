package com.example.auth_service.core.exceptions.specific_case;

import com.example.auth_service.core.exceptions.base.extendtions.BadRequestException;

public class EmailAlreadyExistsException extends BadRequestException{

    public EmailAlreadyExistsException(){
        super("Email already exists!");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
    
}
