package com.lusa.budrio.service.impl;

import com.lusa.budrio.model.Evento;
import com.lusa.budrio.model.Messaggio;
import com.lusa.budrio.model.Utente;
import com.lusa.budrio.repository.EventoRepository;
import com.lusa.budrio.repository.MessaggioRepository;
import com.lusa.budrio.service.MessaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessaggioServiceImpl implements MessaggioService {

    @Autowired
    MessaggioRepository messaggioRepository;

    @Autowired
    EventoRepository eventoRepository;

    @Override
    public Messaggio createMessaggio(Long idEvento, Messaggio messaggio, Utente utente) {
        Evento evento = eventoRepository.findOne(idEvento);

        if(evento != null) {
            messaggio.setEvento(evento);
            messaggio.setUtente(utente);

            return messaggioRepository.save(messaggio);
        }
        return null;
    }

    @Override
    public List<Messaggio> getMessaggiByEvento(Long idEvento) {
        Evento evento = eventoRepository.findOne(idEvento);

        if(evento != null) {
            return messaggioRepository.findAll(new Sort("data"));
        }
        return null;
    }
}
