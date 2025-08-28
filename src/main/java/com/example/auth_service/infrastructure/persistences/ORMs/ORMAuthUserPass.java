package com.example.auth_service.infrastructure.persistences.ORMs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth_userpass")
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class ORMAuthUserPass extends ORMBase{
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

   @OneToOne
   @JoinColumn(name = "auth_provider_id", unique = true)
   private ORMAuthProvider authProvider;

    @Column(name = "password_hashed", nullable = false)
    private String passwordHashed;
}
