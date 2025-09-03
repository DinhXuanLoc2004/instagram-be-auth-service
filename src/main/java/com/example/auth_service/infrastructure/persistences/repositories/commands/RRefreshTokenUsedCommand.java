package com.example.auth_service.infrastructure.persistences.repositories.commands;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_service.infrastructure.persistences.ORMs.ORMRefreshTokenUsed;

@Repository
public interface RRefreshTokenUsedCommand extends JpaRepository<ORMRefreshTokenUsed, Long>{
    
}
