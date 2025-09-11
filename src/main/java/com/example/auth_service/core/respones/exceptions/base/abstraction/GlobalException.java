package com.example.auth_service.core.respones.exceptions.base.abstraction;

import org.springframework.http.HttpStatus;

public abstract class GlobalException extends RuntimeException{
    private final HttpStatus httpStatus;

    public GlobalException(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
