package com.lusa.budrio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "utente")
@Data @NoArgsConstructor @AllArgsConstructor
public class Utente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "utente")
    private List<Evento> eventi;

    @OneToMany(mappedBy = "utente")
    private List<Messaggio> messaggi;

    @OneToMany(mappedBy = "utente")
    private List<Notizia> notizie;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
                name = "utente_ruolo",
                joinColumns = @JoinColumn(
                        name = "utenteId", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "ruoloId", referencedColumnName = "id"))
    private List<Ruolo> ruoli;
}
