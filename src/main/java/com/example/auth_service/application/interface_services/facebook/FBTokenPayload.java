package com.example.auth_service.application.interface_services.facebook;

import lombok.Data;

@Data
public class FBTokenPayload {
    private String iss;
    private String aud;
    private String sub;

    private Long iat;
    private Long exp;

    private String jti;
    private String nonce;

    private String email;
    private String user_birthday;

    private String given_name;
    private String family_name;
    private String name;
    private String picture;
}
