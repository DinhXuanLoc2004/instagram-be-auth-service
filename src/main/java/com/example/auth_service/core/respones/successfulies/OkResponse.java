package com.example.auth_service.core.respones.successfulies;

import org.springframework.http.HttpStatus;

public class OkResponse<T> extends SuccessfulResponse<T>{
    private static final HttpStatus statusOk = HttpStatus.OK;

    public OkResponse(String message, T metadata) {
        super(statusOk, message, metadata);
    }

    public OkResponse(T metadata){
        super(statusOk, metadata);
    }
    
}
