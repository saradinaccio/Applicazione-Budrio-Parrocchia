package com.lusa.budrio.repository;

import com.lusa.budrio.model.Sessione;
import com.lusa.budrio.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessioneRepository extends JpaRepository<Sessione, Long> {

    Sessione findByToken(String token);

    Sessione findByUtente(Utente utente);
}
