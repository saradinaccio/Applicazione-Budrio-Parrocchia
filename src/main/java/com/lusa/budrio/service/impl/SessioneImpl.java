package com.lusa.budrio.service.impl;

import com.lusa.budrio.error.InvalidPasswordException;
import com.lusa.budrio.error.ObjectNotFoundException;
import com.lusa.budrio.model.Sessione;
import com.lusa.budrio.model.Utente;
import com.lusa.budrio.repository.SessioneRepository;
import com.lusa.budrio.repository.UtenteRepository;
import com.lusa.budrio.service.SessioneService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class SessioneImpl implements SessioneService {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    SessioneRepository sessioneRepository;

    @Override
    public Sessione login(String email, String password) {
        Utente utente = utenteRepository.findByEmail(email);

        if(utente != null) {
            if(utente.getPassword().equals(password)) {
                sessioneRepository.save(new Sessione(UUID.randomUUID().toString(), utente));
            }
            else {
                throw new InvalidPasswordException();
            }
        }
        throw new ObjectNotFoundException("Non è stato trovato nessun utente con questo indirizzo email: " + email);
    }

    @Override
    public void logout(String token) {
        Sessione sessione = sessioneRepository.findByToken(token);

        if(sessione != null) {
            sessioneRepository.delete(sessione);
        }
        else {
            throw new ObjectNotFoundException("Non è stato trovato nessun utente in sessione");
        }
    }
}
