package com.lusa.budrio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notizia")
@Data @NoArgsConstructor @AllArgsConstructor
public class Notizia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    private String nome;

    private Date data;

    private Date ora;

    private String descrizione;

    @ManyToOne
    @JoinColumn(name="utenteId")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name="sezioneId")
    private Sezione sezione;
}
