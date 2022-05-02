package com.esercizio.utenti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

/**
 * Entity user
 *
 * @author lorenzoBiraghi
 */



@AllArgsConstructor
@NoArgsConstructor
@Data
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

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    /**
     * <p>Join many to many with table "users_addresses"</p>
     * List of User's address
     */
    @ManyToMany
    @JoinTable(name = "users_addresses", joinColumns =
        @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns =
        @JoinColumn(name = "address_id", referencedColumnName = "id"))
    public List<Address> addresses;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "users_roles", joinColumns =
    @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns =
    @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<RoleAuth> roles = new ArrayList<>();
}
