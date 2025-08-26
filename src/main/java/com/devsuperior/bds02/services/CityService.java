package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityDTO> findAll() {
        List<City> list = cityRepository.findAll(Sort.by("name"));
        return list.stream().map(CityDTO::new).toList();
    }

    public CityDTO insert(CityDTO dto) {
        City entity = new City();
        entity.setName(dto.getName());
        entity = cityRepository.save(entity);

        return new CityDTO(entity);
    }

    public void delete(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource Not Found Id: " + id);
        }

        try {
            cityRepository.deleteById(id);

        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial:");
        }
    }
}
