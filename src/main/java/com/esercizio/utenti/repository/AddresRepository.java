package com.esercizio.utenti.repository;

import com.esercizio.utenti.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddresRepository extends JpaRepository<Address, Long> {
}
