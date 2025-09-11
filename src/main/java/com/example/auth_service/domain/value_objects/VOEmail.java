package com.example.auth_service.domain.value_objects;

public class VOEmail {
    private final String value;
    private static final String patternEmail = "^[A-Za-z0-9+_.-]+@(.+)$";

    private VOEmail(String value){
         if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!value.matches(patternEmail)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.value = value.toLowerCase();
    }

    public static VOEmail create(String email){
        return new VOEmail(email);
    }

    public String getValue(){
        return this.value;
    }

}
