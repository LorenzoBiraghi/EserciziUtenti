package com.esercizio.utenti;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utente_id")
    public Long id;

    @Column(name = "utente_nome")
    public String nome;

    @Column(name = "utente_cognome")
    public String cognome;

    @Column(name = "utente_email")
    public String email;

    @Column(name = "utente_telefono")
    public String telefono;

    @Column(name = "utente_indirizzo")
    public String indirizzo;
}
