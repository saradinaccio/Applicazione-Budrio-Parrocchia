package com.lusa.budrio.controller;

import com.lusa.budrio.model.*;
import com.lusa.budrio.service.SessioneService;
import com.lusa.budrio.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UtenteController {

    @Autowired
    SessioneService sessioneService;

    @Autowired
    UtenteService utenteService;

    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping("/login")
    public Risposta<Login> login(@RequestBody Utente utente) {
        Risposta<Login> risposta = new Risposta<>();
        Sessione sessione = sessioneService.login(utente.getEmail(), utente.getPassword());

        if(sessione != null) {
            risposta = creaSessione(sessione);
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Login fallita! Credenziali non corrette.");
        }
        return risposta;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping("/{token}/logout")
    public Risposta logout(@PathVariable String token) {
        Risposta risposta = new Risposta(true, "Logout avvenuto con successo!");
        sessioneService.logout(token);

        return risposta;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping("/utente")
    public Risposta<Login> registrazione(@RequestBody Utente nuovoUtente) {
        Utente utente = utenteService.createUtente(nuovoUtente);
        Risposta<Login> risposta = new Risposta<>();

        if(utente != null) {
            Sessione sessione = sessioneService.creaSessione(utente);

            if(sessione != null) {
                risposta = creaSessione(sessione);
            }
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Registrazione fallita! E' già stato inserito un utente con questa email.");
        }
        return risposta;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @PutMapping("/{token}/utente")
    private Risposta<Login> updateUtente(@PathVariable String token, @RequestBody Utente nuovoUtente) {
        Utente utente = utenteService.updateUtente(token, nuovoUtente);
        Risposta<Login> risposta = new Risposta<>();

        if(utente != null) {
            risposta.setRisultato(true);
            risposta.setMessaggio("Aggiornamento avvenuto con successo!");

            Login login = new Login();

            login.setNome(utente.getNome());
            login.setCognome((utente.getCognome()));
            login.setEmail(utente.getEmail());
            login.setToken(token);

            risposta.setData(login);
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Aggiornamento fallito! Non risulti essere loggato.");
        }
        return risposta;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping("/{token}/utente/cambiaPassword")
    public Risposta cambiaPassword(@PathVariable String token, @RequestBody Password password) {
        Risposta risposta = new Risposta();

        Sessione sessione = sessioneService.checkLogin(token);

        if(sessione != null) {
            if(utenteService.checkVecchiaPasswordValida(sessione, password)) {
                utenteService.cambiaPassword(sessione, password);

                risposta.setRisultato(true);
                risposta.setMessaggio("Aggiornamento avvenuto con successo!");
            }
            else {
                risposta.setRisultato(false);
                risposta.setMessaggio("Aggiornamento fallito! Vecchia password non corretta!.");
            }
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Aggiornamento fallito! Non risulti essere loggato.");
        }
        return risposta;
    }

    private Risposta<Login> creaSessione(Sessione sessione) {
        Risposta<Login> risposta = new Risposta<>();

        risposta.setRisultato(true);
        risposta.setMessaggio("Operazione avvenuta con successo!");

        Login login = new Login();

        login.setNome(sessione.getUtente().getNome());
        login.setCognome((sessione.getUtente().getCognome()));
        login.setEmail(sessione.getUtente().getEmail());
        login.setToken(sessione.getToken());

        risposta.setData(login);

        return risposta;
    }
}
