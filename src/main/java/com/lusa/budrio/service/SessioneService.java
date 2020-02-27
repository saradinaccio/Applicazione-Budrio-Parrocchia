package com.lusa.budrio.service;

import com.lusa.budrio.model.Sessione;
import com.lusa.budrio.model.Utente;

public interface SessioneService {

    Sessione checkLogin(String token);

    Sessione creaSessione(Utente utente);

    Sessione login(String email, String password);

    void logout(String token);
}
