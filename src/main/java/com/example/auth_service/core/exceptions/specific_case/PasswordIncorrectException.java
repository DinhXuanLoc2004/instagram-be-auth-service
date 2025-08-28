package com.example.auth_service.core.exceptions.specific_case;

import com.example.auth_service.core.exceptions.base.extendtions.BadRequestException;

public class PasswordIncorrectException extends BadRequestException{

    public PasswordIncorrectException(){
        super("Password incorrect!");
    }

    public PasswordIncorrectException(String message) {
        super(message);
    }
    
}
