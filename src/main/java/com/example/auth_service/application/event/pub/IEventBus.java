package com.example.auth_service.application.event.pub;

import com.example.auth_service.domain.domain_events.AccountCrearedEvent;

public interface IEventBus {
    public void accountCreated(AccountCrearedEvent profile);
}
