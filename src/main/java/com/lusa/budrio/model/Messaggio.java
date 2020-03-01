package com.lusa.budrio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "testo", nullable = false)
    private String testo;

    @Temporal(TemporalType.DATE)
    @Column(name = "data", nullable = false)
    private Date data;

    @JsonFormat(pattern = "HH:mm")
    @Temporal(TemporalType.TIME)
    @Column(name = "ora", nullable = false)
    private Date ora;

    @ManyToOne
    @JoinColumn(name="utenteId")
    private Utente utente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="eventoId")
    private Evento evento;
}