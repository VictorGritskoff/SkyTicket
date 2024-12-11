package com.example.skyticketdemo.service;


import com.example.skyticketdemo.dto.AirlineDTO;
import com.example.skyticketdemo.entity.Airline;
import com.example.skyticketdemo.mapper.AirlineMapper;
import com.example.skyticketdemo.repository.impl.AirlineRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AirlineService {
    private final AirlineRepositoryImpl airlineRepository;
    private final AirlineMapper airlineMapper;

    public AirlineService(AirlineRepositoryImpl airlineRepository, AirlineMapper airlineMapper) {
        this.airlineRepository = airlineRepository;
        this.airlineMapper = airlineMapper;
    }

    public AirlineDTO getAirlineById(Long id) {
        Airline airline = airlineRepository.findById(id);
        isEntityNull(airline, id);
        return airlineMapper.toDto(airline);
    }

    public List<AirlineDTO> getAllAirlines() {
        List<Airline> airlines = airlineRepository.findAll();
        return airlines.stream()
                .map(airlineMapper::toDto)
                .collect(Collectors.toList());
    }

    public void createAirline(AirlineDTO airlineDTO) {
        Airline airline = airlineMapper.toEntity(airlineDTO);
        airlineRepository.save(airline);
        log.info("Авиалиния с id {} успешно сохранёна.", airline.getAirlineID());
    }

    public void updateAirline(Long id, AirlineDTO airlineDTO) {
        Airline existingAirline = airlineRepository.findById(id);
        isEntityNull(existingAirline, id);

        Airline updatedAirline = airlineMapper.toEntity(airlineDTO);
        updatedAirline.setAirlineID(id);

        airlineRepository.update(updatedAirline);
        log.info("Авиалиния с id {} успешно обновлена.", id);
    }

    public void deleteAirline(Long id) {
        Airline airline = airlineRepository.findById(id);
        isEntityNull(airline, id);
        airlineRepository.delete(airline);
        log.info("Авиалиния с id {} успешно удалена.", id);
    }

    private void isEntityNull(Object entity, Long id) {
        if (entity == null) {
            log.error("Airline with id {} not found", id);
            throw new EntityNotFoundException("Авиалиния с id " + id + " не найдена.");
        }
    }
}
