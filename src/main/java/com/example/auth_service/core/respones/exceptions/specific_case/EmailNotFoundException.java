package com.example.auth_service.core.respones.exceptions.specific_case;

import com.example.auth_service.core.respones.exceptions.base.extendtions.NotFoundException;

public class EmailNotFoundException extends NotFoundException{

    public EmailNotFoundException(String message){
        super(message);
    }

    public EmailNotFoundException() {
        super("Email not found");
    }
    
}
