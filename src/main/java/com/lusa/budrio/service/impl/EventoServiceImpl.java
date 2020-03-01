package com.lusa.budrio.service.impl;

import com.lusa.budrio.model.Evento;
import com.lusa.budrio.model.Sessione;
import com.lusa.budrio.model.Sezione;
import com.lusa.budrio.repository.EventoRepository;
import com.lusa.budrio.repository.SessioneRepository;
import com.lusa.budrio.repository.SezioneRepository;
import com.lusa.budrio.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventoServiceImpl implements EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    SessioneRepository sessioneRepository;

    @Autowired
    SezioneRepository sezioneRepository;

    @Override
    public Evento createEventoImportante(String token, Evento evento) {
        Sessione sessione = sessioneRepository.findByToken(token);

        if(sessione != null) {
            evento.setUtente(sessione.getUtente());
            return eventoRepository.save(evento);
        }
        return null;
    }

    @Override
    public Evento updateEvento(Long id, Evento nuovoEvento) {
        Evento evento = eventoRepository.findOne(id);

        if(evento != null) {
            evento.setNome(nuovoEvento.getNome());
            evento.setDescrizione(nuovoEvento.getDescrizione());
            evento.setData(nuovoEvento.getData());
            evento.setOra(nuovoEvento.getOra());
            evento.setLuogo(nuovoEvento.getLuogo());
            evento.setCosto(nuovoEvento.getCosto());

            return eventoRepository.save(evento);
        }
        return null;
    }

    @Override
    public List<Evento> getEventiImportanti(String token) {
        Sessione sessione = sessioneRepository.findByToken(token);

        if(sessione != null) {
            return eventoRepository.getEventiImportanti();

        }
        return null;
    }

    @Override
    public boolean deleteEvento(Long id) {
        Evento evento = eventoRepository.findOne(id);

        if(evento != null) {
            eventoRepository.delete(evento);
            return true;
        }
        return false;
    }

    @Override
    public Evento createEventoBySezione(Evento evento, Long idSezione) {
        Sezione sezione = sezioneRepository.findOne(idSezione);

        if(sezione != null) {
            evento.setSezione(sezione);

            return eventoRepository.save(evento);
        }
        return null;
    }

    @Override
    public List<Evento> getEventiBySezione(Long idSezione) {
        Sezione sezione = sezioneRepository.findOne(idSezione);

        if(sezione != null) {
            return eventoRepository.getEventiBySezione(sezione);
        }
        return null;
    }
}
