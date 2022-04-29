package com.esercizio.utenti.repository;

import com.esercizio.utenti.entity.RoleAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleAuthRepository extends JpaRepository<RoleAuth, Long> {
    RoleAuth findByName(String name);
}
