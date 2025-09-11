package com.example.auth_service.core.respones.exceptions.specific_case;

import com.example.auth_service.core.respones.exceptions.base.extendtions.ConflictException;

public class AlreadyVerifiedException extends ConflictException{

    public AlreadyVerifiedException(){
        super(AlreadyVerifiedException.class.getSimpleName());
    }

    public AlreadyVerifiedException(String message) {
        super(message);
    }
    
}
