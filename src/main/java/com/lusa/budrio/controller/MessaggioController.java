package com.lusa.budrio.controller;

import com.lusa.budrio.model.Evento;
import com.lusa.budrio.model.Messaggio;
import com.lusa.budrio.model.Risposta;
import com.lusa.budrio.model.Sessione;
import com.lusa.budrio.service.MessaggioService;
import com.lusa.budrio.service.SessioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessaggioController {

    @Autowired
    MessaggioService messaggioService;

    @Autowired
    SessioneService sessioneService;

    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping("/{token}/messaggio/evento/{idEvento}")
    public Risposta createMessaggio(@PathVariable String token, @PathVariable Long idEvento, @RequestBody Messaggio nuovoMessaggio) {
        Risposta<Messaggio> risposta = new Risposta<>();
        Sessione sessione = sessioneService.checkLogin(token);

        if(sessione != null) {
            Messaggio messaggio = messaggioService.createMessaggio(idEvento, nuovoMessaggio, sessione.getUtente());

            if(messaggio != null) {
                risposta.setRisultato(true);
                risposta.setMessaggio("Messaggio creato con successo!");
                risposta.setData(messaggio);
            }
            else {
                risposta.setRisultato(false);
                risposta.setMessaggio("Creazione messaggio fallita! Non è presente alcun evento con queste specifiche.");
            }
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Creazione messaggio fallita! Non risulti essere loggato.");
        }
        return risposta;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping("/{token}/messaggio/evento/{idEvento}")
    public Risposta getMessaggiByEvento(@PathVariable String token, @PathVariable Long idEvento) {
        Risposta<List<Messaggio>> risposta = new Risposta<>();
        Sessione sessione = sessioneService.checkLogin(token);

        if(sessione != null) {
            List<Messaggio> messaggi = messaggioService.getMessaggiByEvento(idEvento);

            if(messaggi != null) {
                risposta.setRisultato(true);
                risposta.setMessaggio("Messaggi recuperati con successo!");
                risposta.setData(messaggi);
            }
            else {
                risposta.setRisultato(false);
                risposta.setMessaggio("Recupero messaggio fallito! Non è presente alcun evento con queste specifiche.");
            }
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Creazione messaggio fallita! Non risulti essere loggato.");
        }
        return risposta;
    }
}
