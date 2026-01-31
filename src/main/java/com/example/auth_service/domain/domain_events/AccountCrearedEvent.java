package com.example.auth_service.domain.domain_events;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountCrearedEvent {
    private final UUID accountId;
    private final String avatarURL;
    private final String displayName;
    private final boolean blueCheckMark;
}
