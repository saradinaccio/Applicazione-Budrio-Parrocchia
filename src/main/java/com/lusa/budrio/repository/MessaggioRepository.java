package com.lusa.budrio.repository;

import com.lusa.budrio.model.Messaggio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessaggioRepository extends JpaRepository<Messaggio, Long> {
}
