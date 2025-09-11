package com.example.auth_service.core.respones.exceptions.specific_case;

import com.example.auth_service.core.respones.exceptions.base.extendtions.BadRequestException;

public class OTPExpiredException extends BadRequestException{

    public OTPExpiredException() {
        super("OTP expired!");
    }

    public OTPExpiredException(String message) {
        super(message);
    }
    
}
