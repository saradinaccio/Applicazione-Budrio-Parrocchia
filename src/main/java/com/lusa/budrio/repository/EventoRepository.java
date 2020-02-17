package com.lusa.budrio.repository;

import com.lusa.budrio.model.Evento;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("select e from Evento e where :sezione is null")
    List<Evento> getEventiImportanti(Sort sort);

    @Query("select e from Evento e where e.sezione.Id = ?2")
    List<Evento> getEventiBySezione(Sort sort, Long sezioneId);
}
