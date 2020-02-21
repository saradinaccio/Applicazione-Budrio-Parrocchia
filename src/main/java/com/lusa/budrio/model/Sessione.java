package com.lusa.budrio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "sessione")
@Data @NoArgsConstructor @AllArgsConstructor
public class Sessione {

    private static final int SCADENZA = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    private String token;

    @OneToOne(mappedBy = "sessione")
    private Utente utente;

    private Date dataScadenza;

    public Sessione(final String token, final Utente utente) {
        super();

        this.token = token;
        this.utente = utente;
        this.dataScadenza = checkdataScadenza(SCADENZA);
    }

    private Date checkdataScadenza(final int dataScadenzaInMinuti) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, dataScadenzaInMinuti);

        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.dataScadenza = checkdataScadenza(SCADENZA);
    }
}
