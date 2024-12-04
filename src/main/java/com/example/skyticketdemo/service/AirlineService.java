package com.example.skyticketdemo.service;


import com.example.skyticketdemo.dto.AirlineDTO;
import com.example.skyticketdemo.entity.Airline;
import com.example.skyticketdemo.mapper.AirlineMapper;
import com.example.skyticketdemo.repository.interfac.AirlineRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AirlineService {
    private final AirlineRepository airlineRepository;
    private final AirlineMapper airlineMapper;

    public AirlineService(AirlineRepository airlineRepository, AirlineMapper airlineMapper) {
        this.airlineRepository = airlineRepository;
        this.airlineMapper = airlineMapper;
    }

    public AirlineDTO getAirlineById(Long id) {
        Airline airline = airlineRepository.findById(id);
        if (airline == null) {
            throw new EntityNotFoundException("Авиалиния с id " + id + " не найдена.");
        }
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
        if (existingAirline == null) {
            throw new EntityNotFoundException("Авиалиния с id " + id + " не найдена.");
        }

        Airline updatedAirline = airlineMapper.toEntity(airlineDTO);
        updatedAirline.setAirlineID(id);

        airlineRepository.update(updatedAirline);
        log.info("Авиалиния с id {} успешно обновлена.", id);
    }

    public void deleteAirline(Long id) {
        Airline airline = airlineRepository.findById(id);
        if (airline == null) {
            throw new EntityNotFoundException("Авиалиния с id " + id + " не найдена.");
        }
        airlineRepository.delete(airline);
        log.info("Авиалиния с id {} успешно удалена.", id);
    }
}
