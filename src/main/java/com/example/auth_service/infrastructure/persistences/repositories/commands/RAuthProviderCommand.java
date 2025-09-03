package com.example.auth_service.infrastructure.persistences.repositories.commands;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_service.infrastructure.persistences.ORMs.ORMAuthProvider;

@Repository
public interface RAuthProviderCommand extends JpaRepository<ORMAuthProvider, UUID>{
    
}
