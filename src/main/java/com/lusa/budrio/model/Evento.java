package com.lusa.budrio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="EVENTO")
@Getter
@Setter
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Date data;

    private Date ora;

    private String descrizione;

    private String luogo;

    private Float costo;

    @ManyToOne
    @JoinColumn(name="UTENTEID")
    private Utente utente;

    public Evento(Long id, String nome, Date data, Date ora, String descrizione, String luogo, Float costo, Utente utente) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.ora = ora;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.costo = costo;
        this.utente = utente;
    }
}
