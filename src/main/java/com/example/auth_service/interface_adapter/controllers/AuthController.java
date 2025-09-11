package com.example.auth_service.interface_adapter.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.application.ports.inputs.SignUpInput;
import com.example.auth_service.application.ports.inputs.login_inputs.extensions.SignInWithFacebookInput;
import com.example.auth_service.application.ports.inputs.login_inputs.extensions.SignInWithUserPassInput;
import com.example.auth_service.application.ports.outputs.JWTOutput;
import com.example.auth_service.application.usecases.SignUpUsecase;
import com.example.auth_service.application.usecases.login_usecase.SignInUsecase;
import com.example.auth_service.application.usecases.login_usecase.strategy.implementations.SignInWithFacebookStrategy;
import com.example.auth_service.application.usecases.login_usecase.strategy.implementations.SignInWithUserPassStrategy;
import com.example.auth_service.core.respones.successfulies.CreatedResponse;
import com.example.auth_service.core.respones.successfulies.OkResponse;
import com.example.auth_service.core.respones.successfulies.ResponeFactory;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final SignUpUsecase signUpUsecase;
    private final SignInWithUserPassStrategy signInWithUserPassStrategy;
    private final SignInWithFacebookStrategy signInWithFacebookStrategy;

    @PostMapping("/sign-in/userpass")
    public ResponseEntity<OkResponse<JWTOutput>> signInWithUserpass(@RequestBody SignInWithUserPassInput input) {
        SignInUsecase<SignInWithUserPassInput> signInUsecase = new SignInUsecase<>(signInWithUserPassStrategy);
        return ResponeFactory.ok(signInUsecase.execute(input));
    }

    @PostMapping("/sign-in/faceboook")
    public ResponseEntity<OkResponse<JWTOutput>> signInWithFacebook(@RequestBody SignInWithFacebookInput input) {
        SignInUsecase<SignInWithFacebookInput> signInUsecase = new SignInUsecase<>(signInWithFacebookStrategy);
        return ResponeFactory.ok(signInUsecase.execute(input));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<CreatedResponse<JWTOutput>> signUp(@RequestBody SignUpInput input) {
        return ResponeFactory.created(signUpUsecase.execute(input));
    }
}
