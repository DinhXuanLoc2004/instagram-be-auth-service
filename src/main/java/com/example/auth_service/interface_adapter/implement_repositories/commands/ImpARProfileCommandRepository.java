package com.example.auth_service.interface_adapter.implement_repositories.commands;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.auth_service.application.interface_repositories.commands.IProfileARCommandRepo;
import com.example.auth_service.domain.aggregate_roots.ProfileAR;
import com.example.auth_service.infrastructure.persistences.ORMs.ORMProfile;
import com.example.auth_service.infrastructure.persistences.repositories.commands.RProfileCommand;

import lombok.AllArgsConstructor;

@Component
@Transactional
@AllArgsConstructor
public class ImpARProfileCommandRepository implements IProfileARCommandRepo {
    private final RProfileCommand profileCommand;

    @Override
    public void save(ProfileAR data) {
        ORMProfile profile = new ORMProfile(null, data.getAccountId(), data.getUsername(), data.getDisplayName(), data.getAvatarURL(), data.getBirthday(), data.getIsBlueCheckMark());
        profileCommand.save(profile);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
