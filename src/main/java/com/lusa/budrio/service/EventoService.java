package com.lusa.budrio.service;

import com.lusa.budrio.model.Evento;

import java.util.List;

public interface EventoService {

    Evento createEventoImportante(String token, Evento evento);

    Evento updateEvento(Long id, Evento nuovoEvento);

    List<Evento> getEventiImportanti(String token);

    boolean deleteEvento(Long id);

    Evento createEventoBySezione(Evento evento, Long idSezione);

    List<Evento> getEventiBySezione(Long idSezione);
}
