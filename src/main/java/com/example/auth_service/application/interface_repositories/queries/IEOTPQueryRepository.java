package com.example.auth_service.application.interface_repositories.queries;

import java.util.Optional;

import com.example.auth_service.application.interface_repositories.queries.views.VOTP;

public interface IEOTPQueryRepository {
    Optional<VOTP> findByUserId(String userId);
}
