package com.esercizio.utenti;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long id;

    @Column
    public String firstname;

    @Column
    public String lastname;

    @Column
    public String email;

    @Column
    public String telephone;

    @Column
    public String address;
}
