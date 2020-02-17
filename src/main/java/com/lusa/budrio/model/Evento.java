package com.lusa.budrio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "evento")
@Data @NoArgsConstructor @AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    private String nome;

    private Date data;

    private Date ora;

    private String descrizione;

    private String luogo;

    private Float costo;

    @ManyToOne
    @JoinColumn(name="utenteId")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name="sezioneId")
    private Sezione sezione;

    @OneToMany(mappedBy = "evento")
    private List<Messaggio> messaggi;
}
