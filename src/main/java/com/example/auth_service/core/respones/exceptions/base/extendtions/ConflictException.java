package com.example.auth_service.core.respones.exceptions.base.extendtions;

import org.springframework.http.HttpStatus;

import com.example.auth_service.core.respones.exceptions.base.abstraction.GlobalException;

public class ConflictException extends GlobalException {

    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

}
