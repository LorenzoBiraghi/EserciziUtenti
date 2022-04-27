package com.esercizio.utenti.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long id;

    @Column(length = 25, nullable = false)
    public String firstname;

    @Column(length = 25, nullable = false)
    public String lastname;

    @Column(length = 50, nullable = true)
    public String email;

    @Column(length = 10, nullable = true)
    public String telephone;

    @ManyToMany
    @JoinTable(name = "users_addresses", joinColumns =
    @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns =
    @JoinColumn(name = "address_id", referencedColumnName = "id"))
    public List<Address> addresses;
}
