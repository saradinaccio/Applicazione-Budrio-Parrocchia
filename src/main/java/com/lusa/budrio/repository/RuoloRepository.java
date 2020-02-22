package com.lusa.budrio.repository;

import com.lusa.budrio.model.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuoloRepository extends JpaRepository<Ruolo, Long> {

    Ruolo findByNome(String nome);
}
