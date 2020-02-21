package com.lusa.budrio.controller;

import com.lusa.budrio.model.Evento;
import com.lusa.budrio.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventoController {


    /*
    @PostMapping("/eventi")
    public Risposta createEvento(@RequestBody Evento nuovoEvento) {
        eventoRepository.save(nuovoEvento);

        Risposta<Evento> risposta = new Risposta<>(true, "Evento creato!");
        risposta.setData(nuovoEvento);
        return risposta;
    }

    @PutMapping("eventi/{id}")
    public Risposta updateEvento(@RequestBody Evento evento, @PathVariable Long id) {
        Evento vecchioEvento = eventoRepository.findOne(id);
        Risposta<Evento> risposta = new Risposta<>();

        if(vecchioEvento == null) {
            risposta.setRisultato(false);
            risposta.setMessaggio("Aggiornamento fallito! Evento non trovato.");
        }
        else {
            evento.setId(id);
            eventoRepository.save(evento);

            risposta.setRisultato(true);
            risposta.setMessaggio("Evento aggiornato!");
            risposta.setData(evento);
        }
        return risposta;
    }

    @DeleteMapping("eventi/{id}")
    public Risposta deleteEvento(@PathVariable Long id) {
        eventoRepository.delete(id);

        Risposta<Evento> risposta = new Risposta<>(true, "Evento eliminato!");
        return risposta;
    }

    @GetMapping("/eventi/{id}")
    public Risposta getEventoById(@PathVariable Long id) {
        Evento evento = eventoRepository.findOne(id);

        Risposta<Evento> risposta = new Risposta<>(true, "Evento trovato!");
        risposta.setData(evento);
        return risposta;
    }

    @GetMapping("/eventi")
    public Risposta getEventiImportanti() {
        List<Evento> eventi = eventoRepository.getEventiImportanti(new Sort("data"));

        Risposta<List<Evento>> risposta = new Risposta<>(true, "Eventi importanti trovati!");
        risposta.setData(eventi);
        return risposta;
    }

    @GetMapping("/eventi/sezione/{id}")
    public Risposta getEventiBySezione(@PathVariable Long id) {
        List<Evento> eventi = eventoRepository.getEventiBySezione(new Sort("data"), id);

        Risposta<List<Evento>> risposta = new Risposta<>(true, "Eventi importanti trovati!");
        risposta.setData(eventi);
        return risposta;
    }
    */

}
