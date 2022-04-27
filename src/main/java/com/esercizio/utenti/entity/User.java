package com.esercizio.utenti.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Entity user
 *
 * @author lorenzoBiraghi
 */



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    /**
     * User's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long id;

    /**
     * User's firstname
     */
    @Column(length = 25, nullable = false)
    public String firstname;

    /**
     * User's lastname
     */
    @Column(length = 25, nullable = false)
    public String lastname;

    /**
     * User's email
     */
    @Column(length = 50, nullable = true)
    public String email;

    /**
     * User's telephone
     */
    @Column(length = 10, nullable = true)
    public String telephone;

    /**
     * <p>Join many to many with table "users_addresses"</p>
     * List of User's address
     */
    @ManyToMany
    @JoinTable(name = "users_addresses", joinColumns =
    @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns =
    @JoinColumn(name = "address_id", referencedColumnName = "id"))
    public List<Address> addresses;
}
