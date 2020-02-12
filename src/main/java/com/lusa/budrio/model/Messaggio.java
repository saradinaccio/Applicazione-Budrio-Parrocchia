package com.lusa.budrio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messaggio")
@Data @NoArgsConstructor @AllArgsConstructor
public class Messaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "testo", nullable = false)
    private String testo;

    @Column(name = "data", nullable = false)
    private Date data;

    @Column(name = "ora", nullable = false)
    private Date ora;

    @ManyToOne
    @JoinColumn(name="utenteId")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name="eventoId")
    private Evento evento;
}
