package com.esercizio.utenti.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    @Column(nullable = false)
    @NotBlank(message = "empty")
    public String firstname;

    /**
     * User's lastname
     */
    @Column(nullable = false)
    @NotBlank
    public String lastname;

    /**
     * User's email
     */
    @Column
    @Email
    public String email;

    /**
     * User's telephone
     */
    @Column
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
