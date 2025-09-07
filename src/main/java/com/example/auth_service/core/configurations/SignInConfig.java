package com.example.auth_service.core.configurations;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.auth_service.application.ports.inputs.login_inputs.abstraction.ASignInInput;
import com.example.auth_service.application.usecases.login_usecase.SignInUsecaseFactory;
import com.example.auth_service.application.usecases.login_usecase.strategy.implementations.SignInWithFacebookStrategy;
import com.example.auth_service.application.usecases.login_usecase.strategy.implementations.SignInWithUserPassStrategy;
import com.example.auth_service.application.usecases.login_usecase.strategy.interfaces.SignInStrategy;
import com.example.auth_service.domain.types.ProviderType;

@Configuration
public class SignInConfig {
    @Bean
    public Map<ProviderType, SignInStrategy<? extends ASignInInput>> signInStrategies(
        SignInWithFacebookStrategy facebookStrategy,
        SignInWithUserPassStrategy userPassStrategy
    ){
        Map<ProviderType, SignInStrategy<? extends ASignInInput>> strategies = new HashMap<>();
        strategies.put(ProviderType.USER_PASS_TYPE, userPassStrategy);
        strategies.put(ProviderType.FACEBOOK_TYPE, facebookStrategy);
        return strategies;
    }

    @Bean
    public SignInUsecaseFactory signInUsecaseFactory(Map<ProviderType, SignInStrategy<? extends ASignInInput>> strategies){
        return new SignInUsecaseFactory(strategies);
    }
}
