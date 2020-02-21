package com.lusa.budrio.controller;

import com.lusa.budrio.model.Login;
import com.lusa.budrio.model.Risposta;
import com.lusa.budrio.model.Sessione;
import com.lusa.budrio.model.Utente;
import com.lusa.budrio.service.SessioneService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtenteController {

    SessioneService sessioneService;

    @PostMapping("/login")
    public Risposta<Login> login(@RequestBody Utente utente) {
        Sessione sessione = sessioneService.login(utente.getEmail(), utente.getPassword());
        Risposta<Login> risposta = new Risposta<>();

        if(sessione != null) {
            risposta.setRisultato(true);
            risposta.setMessaggio("Login avvenuto con successo!");
            Login login = new Login();

            login.setNome(sessione.getUtente().getNome());
            login.setCognome((sessione.getUtente().getCognome()));
            login.setEmail(sessione.getUtente().getEmail());
            login.setToken(sessione.getToken());

            risposta.setData(login);
            return risposta;
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Login fallito!");
            return risposta;
        }
    }
}
