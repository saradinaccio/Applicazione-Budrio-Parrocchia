package com.lusa.budrio.controller;

import com.lusa.budrio.model.Login;
import com.lusa.budrio.model.Risposta;
import com.lusa.budrio.model.Sessione;
import com.lusa.budrio.model.Utente;
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
        return creaSessione(utente, "Login");
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping
    public Risposta<Login> registrazione(@RequestBody Utente nuovoUtente) {
        Utente utente = utenteService.createUtente(nuovoUtente);
        Risposta<Login> risposta = new Risposta<>();

        if(utente != null) {
            risposta = creaSessione(utente, "Registrazione");
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Registrazione fallita!");
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

    private Risposta<Login> creaSessione(Utente utente, String operazione) {
        Sessione sessione = sessioneService.login(utente.getEmail(), utente.getPassword());
        Risposta<Login> risposta = new Risposta<>();

        if(sessione != null) {
            risposta.setRisultato(true);

            if (operazione.equals("Login")) {
                risposta.setMessaggio("Login avvenuta con successo!");
            } else {
                risposta.setMessaggio("Registrazione avvenuta con successo!");
            }

            Login login = new Login();

            login.setNome(sessione.getUtente().getNome());
            login.setCognome((sessione.getUtente().getCognome()));
            login.setEmail(sessione.getUtente().getEmail());
            login.setToken(sessione.getToken());

            risposta.setData(login);
        }
        else {
            risposta.setRisultato(false);

            if (operazione.equals("Login")) {
                risposta.setMessaggio("Email o password errate!");
            } else {
                risposta.setMessaggio("E' già stato creato un profilo con questa email!");
            }
        }
        return risposta;
    }
}
