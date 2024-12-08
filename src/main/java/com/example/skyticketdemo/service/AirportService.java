package com.example.skyticketdemo.service;

import com.example.skyticketdemo.dto.AirportDTO;
import com.example.skyticketdemo.entity.Airport;
import com.example.skyticketdemo.mapper.AirportMapper;
import com.example.skyticketdemo.repository.impl.AirportBaseRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AirportService {
    private final AirportBaseRepositoryImpl airportRepository;
    private final AirportMapper airportMapper;

    public AirportService(AirportBaseRepositoryImpl airportRepository, AirportMapper airportMapper) {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
    }

    public List<AirportDTO> getAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream()
                .map(airportMapper::toDto)
                .collect(Collectors.toList());
    }

    public void createAirport(AirportDTO airportDTO) {
        Airport airport = airportMapper.toEntity(airportDTO);
        airportRepository.save(airport);
        log.info("Аэропорт с id {} успешно сохранён.", airport.getAirportID());
    }

    public void updateAirport(Long id, AirportDTO airportDTO) {
        Airport existingAirport = airportRepository.findById(id);
        if (existingAirport == null) {
            throw new EntityNotFoundException("Аэропорт с id " + id + " не найден.");
        }

        Airport updatedAirport = airportMapper.toEntity(airportDTO);
        updatedAirport.setAirportID(id);

        airportRepository.update(updatedAirport);
        log.info("Аэропорт с id {} успешно обновлён.", id);
    }

    public void deleteAirport(Long id) {
        Airport airport = airportRepository.findById(id);
        if (airport == null) {
            throw new EntityNotFoundException("Аэропорт с id " + id + " не найден.");
        }
        airportRepository.delete(airport);
        log.info("Аэропорт с id {} успешно удалён.", id);
    }
}
