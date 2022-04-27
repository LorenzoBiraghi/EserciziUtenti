package com.esercizio.utenti.repository;

import com.esercizio.utenti.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository adress with Long primary key
 *
 * @author lorenzoBiraghi
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
