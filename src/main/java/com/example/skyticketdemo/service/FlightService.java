package com.example.skyticketdemo.service;

import com.example.skyticketdemo.dto.FlightDTO;
import com.example.skyticketdemo.entity.Airline;
import com.example.skyticketdemo.entity.Airport;
import com.example.skyticketdemo.entity.Flight;
import com.example.skyticketdemo.mapper.FlightMapper;
import com.example.skyticketdemo.repository.impl.AirlineRepositoryImpl;
import com.example.skyticketdemo.repository.impl.AirportRepositoryImpl;
import com.example.skyticketdemo.repository.impl.FlightRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.lang.module.FindException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FlightService {
    private final FlightRepositoryImpl flightRepository;
    private FlightMapper flightMapper;
    private AirportRepositoryImpl airportRepository;
    private AirlineRepositoryImpl airlineRepository;

    public FlightService(FlightRepositoryImpl flightRepository,
                         AirportRepositoryImpl airportRepository,
                         AirlineRepositoryImpl airlineRepository,
                         FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
        this.flightMapper = flightMapper;
    }

    public FlightService(FlightRepositoryImpl flightRepository) {
        this.flightRepository = flightRepository;
    }

    public FlightDTO getFlightById(Long id) {
        Flight flight = flightRepository.findById(id);
        if (flight == null) {
            throw new FindException("Рейс не найден с id: " + id);
        }
        return flightMapper.toDto(flight);
    }

    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    public void createFlight(FlightDTO flightDTO) {
        try {
            flightDTO.setDepartureTime(validateAndFormatDateTime(flightDTO.getDepartureTime()));
            flightDTO.setArrivalTime(validateAndFormatDateTime(flightDTO.getArrivalTime()));

            Flight flight = flightMapper.toEntity(flightDTO);

            Airport departureAirport = airportRepository.findById(flightDTO.getDepartureAirport().getAirportID());
            if (departureAirport == null) {
                throw new IllegalArgumentException("Аэропорт отправления с ID " + flightDTO.getDepartureAirport().getAirportID() + " не найден.");
            }
            flight.setDepartureAirport(departureAirport);

            Airport arrivalAirport = airportRepository.findById(flightDTO.getArrivalAirport().getAirportID());
            if (arrivalAirport == null) {
                throw new IllegalArgumentException("Аэропорт прибытия с ID " + flightDTO.getArrivalAirport().getAirportID() + " не найден.");
            }
            flight.setArrivalAirport(arrivalAirport);

            Airline airline = airlineRepository.findById(flightDTO.getAirline().getAirlineID());
            if (airline == null) {
                throw new IllegalArgumentException("Авиалиния с ID " + flightDTO.getAirline().getAirlineID() + " не найдена.");
            }
            flight.setAirline(airline);

            flightRepository.save(flight);
            log.info("Рейс с номером {} успешно создан.", flight.getFlightNumber());

        } catch (IllegalArgumentException e) {
            log.error("Ошибка создания рейса: {}", e.getMessage());
            throw e;
        }
    }

    public void updateFlight(Long id, FlightDTO flightDTO) {
        try {
            Flight existingFlight = flightRepository.findById(id);
            if (existingFlight == null) {
                throw new EntityNotFoundException("Рейс с ID " + id + " не найден.");
            }

            existingFlight.setFlightNumber(flightDTO.getFlightNumber());
            existingFlight.setDepartureTime(LocalDateTime.parse(flightDTO.getDepartureTime()));
            existingFlight.setArrivalTime(LocalDateTime.parse(flightDTO.getArrivalTime()));

            Airport departureAirport = airportRepository.findById(flightDTO.getDepartureAirport().getAirportID());
            if (departureAirport == null) {
                throw new EntityNotFoundException("Аэропорт отправления с ID " + flightDTO.getDepartureAirport().getAirportID() + " не найден.");
            }
            existingFlight.setDepartureAirport(departureAirport);

            Airport arrivalAirport = airportRepository.findById(flightDTO.getArrivalAirport().getAirportID());
            if (arrivalAirport == null) {
                throw new EntityNotFoundException("Аэропорт прибытия с ID " + flightDTO.getArrivalAirport().getAirportID() + " не найден.");
            }
            existingFlight.setArrivalAirport(arrivalAirport);

            Airline airline = airlineRepository.findById(flightDTO.getAirline().getAirlineID());
            if (airline == null) {
                throw new EntityNotFoundException("Авиалиния с ID " + flightDTO.getAirline().getAirlineID() + " не найдена.");
            }
            existingFlight.setAirline(airline);
            flightRepository.update(existingFlight);

            log.info("Рейс с ID {} успешно обновлён.", id);
        } catch (EntityNotFoundException e) {
            log.error("Ошибка при обновлении рейса: {}", e.getMessage());
            throw e;
        }
    }


    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id);
        if (flight == null) {
            throw new EntityNotFoundException("Рейс по id = {} " + id + " не найден");
        }
        flightRepository.delete(flight);
    }

    private String validateAndFormatDateTime(String dateTime) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateTime, inputFormatter).format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты/времени: " + dateTime);
        }
    }

    public List<Flight> findFlights(String departureCountry, String arrivalCountry, LocalDate departureDate, Integer tickets) {
        if ((departureCountry == null || departureCountry.isEmpty()) &&
                (arrivalCountry == null || arrivalCountry.isEmpty()) &&
                departureDate == null &&
                tickets == null) {
            throw new IllegalArgumentException("Необходимо указать хотя бы один параметр для поиска.");
        }
        return flightRepository.searchFlights(departureCountry, arrivalCountry, departureDate, tickets);
    }

}
