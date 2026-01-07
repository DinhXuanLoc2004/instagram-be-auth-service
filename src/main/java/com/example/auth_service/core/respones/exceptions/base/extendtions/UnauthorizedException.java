package com.example.auth_service.core.respones.exceptions.base.extendtions;

import org.springframework.http.HttpStatus;

import com.example.auth_service.core.respones.exceptions.base.abstraction.GlobalException;

public class UnauthorizedException extends GlobalException{
    public UnauthorizedException(String message){
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
