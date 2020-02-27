package com.lusa.budrio.service;

import com.lusa.budrio.model.Password;
import com.lusa.budrio.model.Sessione;
import com.lusa.budrio.model.Utente;

public interface UtenteService {

    Utente createUtente(Utente utente);

    Utente updateUtente(String token, Utente utente);

    Utente getUtente(String token);

    void deleteUtente(Utente utente);

    boolean checkVecchiaPasswordValida(Sessione sessione, Password password);

    void cambiaPassword(Sessione sessione, Password password);
}
