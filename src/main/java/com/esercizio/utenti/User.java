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
    @Column(name = "user_id")
    public Long id;

    @Column(name = "user_firstname")
    public String firstname;

    @Column(name = "user_lastname")
    public String lastname;

    @Column(name = "user_mail")
    public String mail;

    @Column(name = "user_telephone")
    public String telephone;

    @Column(name = "user_address")
    public String address;
}
