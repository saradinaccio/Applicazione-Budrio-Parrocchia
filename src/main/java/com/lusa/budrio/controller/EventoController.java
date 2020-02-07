package com.lusa.budrio.controller;

import com.lusa.budrio.model.Evento;
import com.lusa.budrio.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventoController {
    @Autowired
    EventoRepository eventoRepository;

    @PostMapping("/eventi")
    public Evento createEvento(@RequestBody Evento nuovoEvento) {
        return eventoRepository.save(nuovoEvento);
    }

    @GetMapping("/eventi/{id}")
    Evento getEventoById(@PathVariable Long id) {
        return eventoRepository.findById(id).get();
    }
}
