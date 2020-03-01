package com.lusa.budrio.service;

import com.lusa.budrio.model.Messaggio;
import com.lusa.budrio.model.Utente;

import java.util.List;

public interface MessaggioService {

    Messaggio createMessaggio(Long idEvento, Messaggio messaggio, Utente utente);

    List<Messaggio> getMessaggiByEvento(Long idEvento);
}
