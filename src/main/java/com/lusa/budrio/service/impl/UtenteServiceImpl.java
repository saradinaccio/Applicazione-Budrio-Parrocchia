package com.lusa.budrio.service.impl;

import com.lusa.budrio.model.Password;
import com.lusa.budrio.model.Sessione;
import com.lusa.budrio.model.Utente;
import com.lusa.budrio.repository.RuoloRepository;
import com.lusa.budrio.repository.SessioneRepository;
import com.lusa.budrio.repository.UtenteRepository;
import com.lusa.budrio.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    RuoloRepository ruoloRepository;

    @Autowired
    SessioneRepository sessioneRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Utente createUtente(Utente utente) {
        if(emailExists(utente.getEmail())) {
            return null;
        }
        else {
            utente.setPassword(passwordEncoder.encode(utente.getPassword()));
            utente.setRuoli(Arrays.asList(ruoloRepository.findByNome("RUOLO_UTENTE")));
            return utenteRepository.save(utente);
        }
    }

    @Override
    public Utente updateUtente(String token, Utente utente) {
        final Sessione sessione = sessioneRepository.findByToken(token);

        if(sessione != null) {
            Utente nuovoUtente = sessione.getUtente();

            nuovoUtente.setNome(utente.getNome());
            nuovoUtente.setCognome(utente.getCognome());
            nuovoUtente.setEmail(utente.getEmail());

            return utenteRepository.save(nuovoUtente);
        }
        return null;
    }

    @Override
    public Utente getUtente(String token) {
        final Sessione sessione = sessioneRepository.findByToken(token);

        if(sessione != null) {
            return sessione.getUtente();
        }
        return null;
    }

    @Override
    public void deleteUtente(Utente utente) {
        final Sessione sessione = sessioneRepository.findByUtente(utente);

        if(sessione != null) {
            sessioneRepository.delete(sessione);
        }

        utenteRepository.delete(utente);
    }

    @Override
    public boolean checkVecchiaPasswordValida(Sessione sessione, Password password) {
        return passwordEncoder.matches(password.getVecchiaPassword(), sessione.getUtente().getPassword());
    }

    @Override
    public void cambiaPassword(Sessione sessione, Password password) {
        sessione.getUtente().setPassword(passwordEncoder.encode(password.getNuovaPassword()));
        utenteRepository.save(sessione.getUtente());
    }

    private boolean emailExists(final String email) {
        return utenteRepository.findByEmail(email) != null;
    }
}
