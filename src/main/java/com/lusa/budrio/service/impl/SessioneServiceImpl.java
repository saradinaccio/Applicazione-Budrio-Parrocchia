package com.lusa.budrio.service.impl;

import com.lusa.budrio.error.ObjectNotFoundException;
import com.lusa.budrio.model.Sessione;
import com.lusa.budrio.model.Utente;
import com.lusa.budrio.repository.SessioneRepository;
import com.lusa.budrio.repository.UtenteRepository;
import com.lusa.budrio.service.SessioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class SessioneServiceImpl implements SessioneService {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    SessioneRepository sessioneRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Sessione checkLogin(String token) {
        return sessioneRepository.findByToken(token);
    }

    @Override
    public Sessione login(String email, String password) {
        Utente utente = utenteRepository.findByEmail(email);

        if(utente != null && passwordEncoder.matches(password, utente.getPassword())) {
            return creaSessione(utente);
        }
        return null;
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

    @Override
    public Sessione creaSessione(Utente utente)  {
        Sessione sessione = new Sessione(UUID.randomUUID().toString(), utente);

        sessioneRepository.save(sessione);

        return sessione;
    }
}
