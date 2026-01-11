package com.example.auth_service.core.respones.exceptions.specific_case;

import com.example.auth_service.core.respones.exceptions.base.extendtions.UnauthorizedException;

public class OIDCInvalidException extends UnauthorizedException{

    public OIDCInvalidException(String message) {
        super(message);
    }

    public OIDCInvalidException() {
        super("OIDC ID InValid");
    }
    
}
