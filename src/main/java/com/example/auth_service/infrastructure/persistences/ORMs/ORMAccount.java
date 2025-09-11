package com.example.auth_service.infrastructure.persistences.ORMs;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ORMAccount extends ORMBase {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "is_verified")
    private boolean isVerified;

    // public ORMAccount(UUID id, String email, boolean isVerified){
    //     this.id = id;
    //     this.email = email;
    //     this.isVerified = isVerified;
    // }
    
}
