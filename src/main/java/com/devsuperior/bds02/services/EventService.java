package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepoditory;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    private final EventRepoditory eventRepoditory;

    public EventService(EventRepoditory eventRepoditory) {
        this.eventRepoditory = eventRepoditory;
    }

    @Transactional
    public EventDTO update(Long id, EventDTO dto) {

        if (!eventRepoditory.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found. Id " + id);
        }

        Event entity = eventRepoditory.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = eventRepoditory.save(entity);

        return new EventDTO(entity);
    }

    public void copyDtoToEntity(EventDTO dto, Event entity) {
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        entity.setCity(new City(dto.getCityId(), null));
    }
}
