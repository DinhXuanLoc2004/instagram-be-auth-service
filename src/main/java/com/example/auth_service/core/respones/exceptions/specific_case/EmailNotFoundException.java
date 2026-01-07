package com.example.auth_service.core.respones.exceptions.specific_case;

import com.example.auth_service.core.respones.exceptions.base.extendtions.NotFoundException;
import com.example.auth_service.core.respones.exceptions.base.extendtions.UnauthorizedException;

public class EmailNotFoundException extends UnauthorizedException{

    public EmailNotFoundException(String message){
        super(message);
    }

    public EmailNotFoundException() {
        super("Incorrect email or password");
        System.out.println("Email not found");
    }
    
}
