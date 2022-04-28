package com.esercizio.utenti.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    @Column(nullable = false)
    @NotBlank
    public String street;

    /**
     * Address' cap
     */
    @Column(nullable = false)
    @NotBlank
    public String cap;

    /**
     * Address' street_number
     */
    @Column
    public Integer street_number;
}
