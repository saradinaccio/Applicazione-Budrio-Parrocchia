package com.lusa.budrio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Temporal(TemporalType.DATE)
    private Date data;

    @JsonFormat(pattern = "HH:mm")
    @Temporal(TemporalType.TIME)
    private Date ora;

    private String descrizione;

    private String luogo;

    private Float costo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="utenteId")
    private Utente utente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="sezioneId")
    private Sezione sezione;

    @JsonIgnore
    @OneToMany(mappedBy = "evento")
    private List<Messaggio> messaggi;
}
