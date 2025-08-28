package com.example.auth_service.core.exceptions.base.extendtions;

import org.springframework.http.HttpStatus;

import com.example.auth_service.core.exceptions.base.abstraction.GlobalException;

public class BadRequestException extends GlobalException{

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
    
}
