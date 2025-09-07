package com.example.auth_service.interface_adapter.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.application.ports.inputs.SignUpInput;
import com.example.auth_service.application.ports.inputs.login_inputs.abstraction.ASignInInput;
import com.example.auth_service.application.ports.outputs.JWTOutput;
import com.example.auth_service.application.usecases.SignUpUsecase;
import com.example.auth_service.application.usecases.login_usecase.SignInUsecase;
import com.example.auth_service.application.usecases.login_usecase.SignInUsecaseFactory;
import com.example.auth_service.domain.types.ProviderType;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final SignInUsecaseFactory signInUsecaseFactory;
    private final SignUpUsecase signUpUsecase;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PostMapping("/sign-in")
    public ResponseEntity<JWTOutput> signIn(@RequestBody ASignInInput input) {
        ProviderType providerType = input.getProviderType();
        SignInUsecase usecase = signInUsecaseFactory.getUsecase(providerType);
        return ResponseEntity.ok(usecase.execute(input));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<JWTOutput> signUp(@RequestBody SignUpInput input) {
        return ResponseEntity.ok(signUpUsecase.execute(input));
    }

}
