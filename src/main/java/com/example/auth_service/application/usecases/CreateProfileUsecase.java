package com.example.auth_service.application.usecases;

import org.springframework.stereotype.Component;

import com.example.auth_service.application.event.pub.IEventBus;
import com.example.auth_service.application.interface_repositories.commands.IProfileARCommandRepo;
import com.example.auth_service.domain.aggregate_roots.ProfileAR;
import com.example.auth_service.domain.domain_events.AccountCrearedEvent;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CreateProfileUsecase {
    private final IProfileARCommandRepo profileARCommandRepo;
    private final IEventBus eventBus;

    public void excute(ProfileAR profileAR) {
        profileARCommandRepo.save(profileAR);

        AccountCrearedEvent newProfile = new AccountCrearedEvent(profileAR.getAccountId(), profileAR.getAvatarURL(), profileAR.getDisplayName(), profileAR.getIsBlueCheckMark());

        eventBus.accountCreated(newProfile);
    }
}
