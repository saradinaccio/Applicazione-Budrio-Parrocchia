package com.lusa.budrio.model;

import lombok.*;

@Getter @Setter @Data @NoArgsConstructor @AllArgsConstructor
public class Risposta<R> {

    public static final Risposta<Object> DEFAULT_RISPOSTA_OK = new Risposta<Object>(true, "OK");
    public static final Risposta<Object> DEFAULT_RISPOSTA_KO = new Risposta<Object>(false, "KO");

    private boolean risultato;
    private R data;
    private String messaggio;

    public Risposta(boolean risultato, String messaggio) {
        this.risultato = risultato;
        this.messaggio = messaggio;
    }
}
