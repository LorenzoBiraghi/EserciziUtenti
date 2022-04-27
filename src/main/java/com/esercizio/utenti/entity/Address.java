package com.esercizio.utenti.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity address
 *
 * @author lorenzoBiraghi
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    /**
     * Address' id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /**
     * Address' street
     */
    @Column(length = 50, nullable = false)
    public String street;

    /**
     * Address' cap
     */
    @Column(length = 5, nullable = false)
    public String cap;

    /**
     * Address' street_number
     */
    @Column(nullable = true)
    public Integer street_number;
}
