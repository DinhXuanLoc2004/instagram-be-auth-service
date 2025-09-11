package com.example.auth_service.core.respones.successfulies;

import org.springframework.http.HttpStatus;

public class CreatedResponse<T> extends SuccessfulResponse<T>{
    public static final HttpStatus statusCreated = HttpStatus.CREATED;

    public CreatedResponse(String message, T metadata) {
        super(statusCreated, message, metadata);
    }

    public CreatedResponse(T metadata){
        super(statusCreated, metadata);
    }
    
}
