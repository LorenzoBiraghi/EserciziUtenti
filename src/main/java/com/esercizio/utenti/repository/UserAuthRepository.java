package com.esercizio.utenti.repository;

import com.esercizio.utenti.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findByUsername(String username);
}
