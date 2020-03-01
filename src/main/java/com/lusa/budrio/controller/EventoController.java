package com.lusa.budrio.controller;

import com.lusa.budrio.model.Evento;
import com.lusa.budrio.model.Risposta;
import com.lusa.budrio.model.Sessione;
import com.lusa.budrio.service.EventoService;
import com.lusa.budrio.service.SessioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventoController {

    @Autowired
    EventoService eventoService;

    @Autowired
    SessioneService sessioneService;


    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping("/{token}/evento")
    public Risposta createEventoImportante(@PathVariable String token, @RequestBody Evento nuovoEvento) {
        Risposta<Evento> risposta = new Risposta<>();
        Evento evento = eventoService.createEventoImportante(token, nuovoEvento);

        if(evento != null) {
            risposta.setRisultato(true);
            risposta.setMessaggio("Evento importante creato con successo!");
            risposta.setData(evento);
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Crezione evento importante fallito! Non risulti essere loggato.");
        }
        return risposta;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @PutMapping("/{token}/evento/{id}")
    public Risposta updateEvento(@PathVariable String token, @PathVariable Long id, @RequestBody Evento nuovoEvento) {
        Risposta<Evento> risposta = new Risposta<>();
        Sessione sessione = sessioneService.checkLogin(token);

        if(sessione != null) {
            Evento evento = eventoService.updateEvento(id, nuovoEvento);

            if(evento != null) {
                risposta.setRisultato(true);
                risposta.setMessaggio("Evento aggiornato con successo!");
                risposta.setData(evento);
            }
            else {
                risposta.setRisultato(false);
                risposta.setMessaggio("Aggiornamento evento fallito! Non è presente alcun evento con queste specifiche.");
            }
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Aggiornamento evento fallito! Non risulti essere loggato.");
        }
        return risposta;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping("/{token}/evento")
    public Risposta getEventiImportanti(@PathVariable String token) {
        Risposta<List<Evento>> risposta = new Risposta<>();
        List<Evento> eventi = eventoService.getEventiImportanti(token);

        if(eventi != null) {
            risposta.setRisultato(true);
            risposta.setMessaggio("Eventi importanti recuperati con successo!");
            risposta.setData(eventi);
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Recupero eventi importanti fallito! Non risulti essere loggato.");
        }
        return risposta;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @DeleteMapping("/{token}/evento/{id}")
    public Risposta deleteEvento(@PathVariable String token, @PathVariable Long id) {
        Risposta risposta = new Risposta();
        Sessione sessione = sessioneService.checkLogin(token);

        if(sessione != null) {

            if(eventoService.deleteEvento(id)) {
                risposta.setRisultato(true);
                risposta.setMessaggio("Evento importante con successo!");
            }
            else {
                risposta.setRisultato(false);
                risposta.setMessaggio("Eliminazione evento fallito! Non è presente alcun evento con tali specifiche.");
            }
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Eliminazione evento fallito! Non risulti essere loggato.");
        }
        return risposta;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping("/{token}/evento/sezione/{idSezione}")
    public Risposta createEventoBySezione(@PathVariable String token, @PathVariable Long idSezione, @RequestBody Evento nuovoEvento) {
        Risposta<Evento> risposta = new Risposta<>();
        Sessione sessione = sessioneService.checkLogin(token);

        if(sessione != null) {
            Evento evento = eventoService.createEventoBySezione(nuovoEvento, idSezione);

            if(evento != null) {
                risposta.setRisultato(true);
                risposta.setMessaggio("Evento creato con successo!");
                risposta.setData(evento);
            }
            else {
                risposta.setRisultato(false);
                risposta.setMessaggio("Recupero eventi importanti fallito! Non è presente alcuna sezione con tali specifiche.");
            }
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Recupero eventi importanti fallito! Non risulti essere loggato.");
        }
        return risposta;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping("/{token}/evento/sezione/{idSezione}")
    public Risposta getEventiBySezione(@PathVariable String token, @PathVariable Long idSezione) {
        Risposta<List<Evento>> risposta = new Risposta<>();
        Sessione sessione = sessioneService.checkLogin(token);

        if(sessione != null) {
            List<Evento> eventi = eventoService.getEventiBySezione(idSezione);

            if(eventi != null) {
                risposta.setRisultato(true);
                risposta.setMessaggio("Eventi recuperati con successo!");
                risposta.setData(eventi);
            }
            else {
                risposta.setRisultato(false);
                risposta.setMessaggio("Eliminazione evento importante fallito! Non è presente alcun evento con queste specifiche.");
            }
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Aggiornamento evento importante fallito! Non risulti essere loggato.");
        }
        return risposta;
    }
}
