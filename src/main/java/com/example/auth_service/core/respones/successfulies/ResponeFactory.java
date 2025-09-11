package com.example.auth_service.core.respones.successfulies;

import org.springframework.http.ResponseEntity;

public class ResponeFactory {
    public static <T> ResponseEntity<OkResponse<T>> ok(String message, T metadata) {
        OkResponse<T> okResponse = new OkResponse<T>(message, metadata);
        return ResponseEntity.ok(okResponse);
    }

    public static <T> ResponseEntity<OkResponse<T>> ok(T metadata) {
        OkResponse<T> okResponse = new OkResponse<T>(metadata);
        return ResponseEntity.ok(okResponse);
    }

    public static <T> ResponseEntity<CreatedResponse<T>> created(String message, T metadata) {
        CreatedResponse<T> createdResponse = new CreatedResponse<T>(message, metadata);
        return ResponseEntity.status(CreatedResponse.statusCreated).body(createdResponse);
    }

    public static <T> ResponseEntity<CreatedResponse<T>> created(T metadata) {
        CreatedResponse<T> createdResponse = new CreatedResponse<T>(metadata);
        return ResponseEntity.status(CreatedResponse.statusCreated).body(createdResponse);
    }
}
