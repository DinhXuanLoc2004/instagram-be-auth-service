package com.example.auth_service.core.respones.exceptions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorResponse {
    private final int status;
    private final String message;
    private final String error;
    private final List<String> stackTrace;

    public ErrorResponse(int status, String message, Exception exception) {
        this.status = status;
        this.message = message;
        this.error = exception.getClass().getSimpleName();
        this.stackTrace = Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString)
                .collect(Collectors.toList());
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public List<String> getStackTrace() {
        return stackTrace;
    }
}
