package com.devsuperior.bds02.repositories;

import com.devsuperior.bds02.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepoditory extends JpaRepository<Event, Long> {
}
