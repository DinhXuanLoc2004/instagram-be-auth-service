package com.example.auth_service.core.respones.exceptions.specific_case;

import com.example.auth_service.core.respones.exceptions.base.extendtions.BadRequestException;
import com.example.auth_service.core.respones.exceptions.base.extendtions.UnauthorizedException;

public class PasswordIncorrectException extends UnauthorizedException{

    public PasswordIncorrectException(){
        super("Incorrect email or password");
        System.out.println("Password incorrect!");
    }

    public PasswordIncorrectException(String message) {
        super(message);
        System.out.println("Password incorrect!");
    }
    
}
