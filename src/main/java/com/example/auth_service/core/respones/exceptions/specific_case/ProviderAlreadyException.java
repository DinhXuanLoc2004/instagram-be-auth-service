package com.example.auth_service.core.respones.exceptions.specific_case;

import com.example.auth_service.core.respones.exceptions.base.extendtions.BadRequestException;

public class ProviderAlreadyException extends BadRequestException{

    public ProviderAlreadyException(){
        super(ProviderAlreadyException.class.getSimpleName());
    }

    public ProviderAlreadyException(String message) {
        super(message);
    }
    
}
