package com.lusa.budrio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Evento> eventi;

    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Messaggio> messaggi;

    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Notizia> notizie;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utente_ruolo",
            joinColumns = @JoinColumn(
                    name = "utenteId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "ruoloId", referencedColumnName = "id"))
    private List<Ruolo> ruoli;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sessioneId", referencedColumnName = "id")
    private Sessione sessione;
}

