package com.example.auth_service.infrastructure.persistences.repositories.queries;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_service.infrastructure.persistences.ORMs.ORMAccount;

@Repository
public interface AccountQueryRepository extends JpaRepository<ORMAccount, UUID>{
    public boolean exexistsByEmail(String email);
}
