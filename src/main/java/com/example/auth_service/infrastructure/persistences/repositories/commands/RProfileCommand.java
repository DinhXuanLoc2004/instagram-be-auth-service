package com.example.auth_service.infrastructure.persistences.repositories.commands;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_service.infrastructure.persistences.ORMs.ORMProfile;

@Repository
public interface RProfileCommand extends JpaRepository<ORMProfile, Long>{
    
}
