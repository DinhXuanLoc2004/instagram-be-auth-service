package com.example.auth_service.infrastructure.persistences.repositories.commands;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_service.infrastructure.persistences.ORMs.ORMAuthUserPass;

@Repository
public interface RAuthUserPassCommand extends JpaRepository<ORMAuthUserPass, Long>{
    
}
