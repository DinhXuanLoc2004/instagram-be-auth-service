package com.example.auth_service.interface_adapter.implement_services;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.auth_service.application.interface_services.facebook.FBTokenPayload;
import com.example.auth_service.application.interface_services.facebook.IFacebookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ImpFacebookService implements IFacebookService {

    
    private final String facebookAppId;
    private final String facebookIssuer;
    private final JwkProvider jwkProvider;

    public ImpFacebookService(
        @Value("${app.facebook.app-id}") String facebookAppId,
        @Value("${app.facebook.issuer-url}") String facebookIssuer,
        JwkProvider jwkProvider
    ) {
        this.facebookAppId = facebookAppId;
        this.facebookIssuer = facebookIssuer;
        this.jwkProvider = jwkProvider;
    }

    @Override
    public boolean verifyOIDC(String oidcID, String rawNonce) {
        try {
            // Decode token (chưa verify)
            DecodedJWT decodedJWT = JWT.decode(oidcID);

            // Lấy public key theo kid
            Jwk jwk = jwkProvider.get(decodedJWT.getKeyId());
            RSAPublicKey publicKey = (RSAPublicKey) jwk.getPublicKey();

            // Build verifier
            Algorithm algorithm = Algorithm.RSA256(publicKey, null);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(facebookIssuer)
                    .withAudience(facebookAppId)
                    .build();

            // Verify chữ ký + standard claims
            DecodedJWT verifiedJwt = verifier.verify(oidcID);

            // Verify nonce (CỰC KỲ QUAN TRỌNG)
            String nonceInToken = verifiedJwt.getClaim("nonce").asString();
            if (nonceInToken == null || !nonceInToken.equals(rawNonce)) {
                return false;
            }

            // (Optional) verify email, exp, sub nếu cần
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public FBTokenPayload getInfoAccountFB(String oidcID) {
        
        try {
            DecodedJWT jwt = JWT.decode(oidcID);
    
            String payload = jwt.getPayload();
            byte[] decoded = Base64.getUrlDecoder().decode(payload);
            
            return new ObjectMapper().readValue(decoded, FBTokenPayload.class);
        } catch (IOException e) {
            throw new RuntimeException("Invalid Facebook ID Token payload", e);
        }
    }

}
