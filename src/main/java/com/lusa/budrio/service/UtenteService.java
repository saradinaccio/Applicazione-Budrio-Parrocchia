package com.lusa.budrio.service;

import com.lusa.budrio.model.Utente;

public interface UtenteService {

    Utente createUtente(Utente utente);

    Utente getUtente(String token);

    void deleteUtente(Utente utente);
}
