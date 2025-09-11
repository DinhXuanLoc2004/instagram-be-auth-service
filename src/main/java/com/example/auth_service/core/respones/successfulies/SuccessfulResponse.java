package com.example.auth_service.core.respones.successfulies;

import org.springframework.http.HttpStatus;

public class SuccessfulResponse<T>{
    private final String messageDefault = "successfully";
    private final int status;
    private final String message;
    private final T metadata;

    public SuccessfulResponse(HttpStatus status, String message, T metadata) {
        this.status = status.value();
        this.message = message;
        this.metadata = metadata;
    }

    public SuccessfulResponse(HttpStatus status, T metadata){
        this.status = status.value();
        this.message = messageDefault;
        this.metadata = metadata;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getMetadata() {
        return metadata;
    }
}
