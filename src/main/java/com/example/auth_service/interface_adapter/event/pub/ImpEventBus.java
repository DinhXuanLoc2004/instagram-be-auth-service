package com.example.auth_service.interface_adapter.event.pub;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.auth_service.application.event.pub.IEventBus;
import com.example.auth_service.domain.domain_events.AccountCrearedEvent;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ImpEventBus implements IEventBus{

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void accountCreated(AccountCrearedEvent account) {
        kafkaTemplate.send("account.created", account);
    }
    
}
