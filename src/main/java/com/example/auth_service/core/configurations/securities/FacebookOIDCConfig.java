package com.example.auth_service.core.configurations.securities;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class FacebookOIDCConfig {
    
    @Value("${app.facebook.config_url}")
    private String facebookConfigUrl;

    @Bean
    public JwkProvider facebookJwkProvider() throws Exception {
        URL jwksUrl = new URI(fetchJwksUri()).toURL();
        return new JwkProviderBuilder(jwksUrl)
            .cached(10, 24, TimeUnit.HOURS)
            .build();
    }

    private String fetchJwksUri() throws Exception {
        URL configUrl = new URI(facebookConfigUrl).toURL();

        try (InputStream in = configUrl.openStream()) {
            JsonNode json = new ObjectMapper().readTree(in);
            return json.get("jwks_uri").asText();
        }
    }
}
