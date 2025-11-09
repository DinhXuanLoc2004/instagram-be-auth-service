package com.example.auth_service.core.respones.exceptions.specific_case;

import com.example.auth_service.core.respones.exceptions.base.extendtions.ConflictException;

public class RefreshTokenDuplicateException extends ConflictException{

    public RefreshTokenDuplicateException(String message) {
        super(message);
    }

    public RefreshTokenDuplicateException() {
        super("This refresh token is being used by another account!");
    }
    
}
