package com.esercizio.utenti.repository;

import com.esercizio.utenti.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * JpaRepository user with Long primary key
 *
 * @author lorenzoBiraghi
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find a user by Username
     * @param username
     * @return User with this Username
     */
    User findByUsername(String username);
}
