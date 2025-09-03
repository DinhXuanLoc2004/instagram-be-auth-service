package com.example.auth_service.infrastructure.persistences.repositories.commands;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_service.infrastructure.persistences.ORMs.ORMAccount;

@Repository
public interface RAccountCommand extends JpaRepository<ORMAccount, UUID>{
    public Optional<ORMAccount> findById(UUID id);

    public Optional<ORMAccount> findByEmail(String email);

    public boolean existsByEmail(String email);
}
