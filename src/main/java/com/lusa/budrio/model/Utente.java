package com.lusa.budrio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="UTENTE")
@Getter
@Setter
public class Utente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cognome;

    private String email;

    private String password;

    @OneToMany(mappedBy = "utente")
    private List<Evento> eventi;

    public Utente(String nome, String cognome, String email, String password, List<Evento> eventi) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.eventi = eventi;
    }
}
