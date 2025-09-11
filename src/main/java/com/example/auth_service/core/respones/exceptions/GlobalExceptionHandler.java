package com.example.auth_service.core.respones.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.auth_service.core.respones.exceptions.base.abstraction.GlobalException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(GlobalException globalException) {
        ErrorResponse errorResponse = new ErrorResponse(globalException.getHttpStatus().value(),
                globalException.getMessage(), globalException);
        return new ResponseEntity<>(errorResponse, globalException.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalException(Exception exception) {
        HttpStatus statusInternal = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(statusInternal.value(), statusInternal.getReasonPhrase(),
                exception);
        return new ResponseEntity<>(errorResponse, statusInternal);
    }
}
