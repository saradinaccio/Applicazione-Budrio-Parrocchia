package com.lusa.budrio.repository;

import com.lusa.budrio.model.Evento;
import com.lusa.budrio.model.Sezione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("select e from Evento e where e.sezione is null and e.data >= CURRENT_DATE order by e.data desc")
    List<Evento> getEventiImportanti();

    @Query("select e from Evento e where e.sezione = :sezione and e.data >= CURRENT_DATE order by e.data desc")
    List<Evento> getEventiBySezione(@Param("sezione") Sezione sezione);
}
