package com.esercizio.utenti.repository;

import com.esercizio.utenti.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
