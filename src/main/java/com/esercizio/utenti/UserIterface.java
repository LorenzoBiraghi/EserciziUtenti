package com.esercizio.utenti;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIterface extends JpaRepository<User, Long> {
}
