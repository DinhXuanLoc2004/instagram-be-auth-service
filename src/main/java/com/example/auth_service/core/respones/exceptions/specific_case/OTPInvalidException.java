package com.example.auth_service.core.respones.exceptions.specific_case;

import com.example.auth_service.core.respones.exceptions.base.extendtions.BadRequestException;

public class OTPInvalidException extends BadRequestException{

    public OTPInvalidException() {
        super("OTP invalid");
    }

    public OTPInvalidException(String message) {
        super(message);
    }
    
}
