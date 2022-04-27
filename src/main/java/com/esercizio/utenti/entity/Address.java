package com.esercizio.utenti.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 50, nullable = false)
    public String street;

    @Column(length = 5, nullable = false)
    public String cap;

    @Column(nullable = true)
    public Integer street_number;
}
