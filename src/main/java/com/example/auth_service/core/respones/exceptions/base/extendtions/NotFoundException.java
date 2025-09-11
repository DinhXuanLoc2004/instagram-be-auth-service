package com.example.auth_service.core.respones.exceptions.base.extendtions;

import org.springframework.http.HttpStatus;

import com.example.auth_service.core.respones.exceptions.base.abstraction.GlobalException;

public class NotFoundException extends GlobalException{

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
    
}
