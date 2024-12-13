package com.example.skyticketdemo.service;


import com.example.skyticketdemo.dto.SeatDTO;
import com.example.skyticketdemo.entity.Seat;
import com.example.skyticketdemo.mapper.SeatMapper;
import com.example.skyticketdemo.repository.impl.SeatRepositoryImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SeatService {
    private final SeatRepositoryImpl seatRepository;
    private SeatMapper seatMapper;

    public SeatService(SeatRepositoryImpl seatRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
    }

    public SeatService(SeatRepositoryImpl seatRepository) {
        this.seatRepository = seatRepository;
    }

    public void createSeats(List<SeatDTO> seatDTOs) {
        List<Seat> seats = seatDTOs.stream()
            .map(seatMapper::toEntity)
            .collect(Collectors.toList());
        seatRepository.saveAll(seats);
        log.info("Места успешно сохранены.");
    }

    public List<SeatDTO> getAllSeats() {
        List<Seat> seats = seatRepository.findAll();
        return seats.stream()
                .map(seatMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteAllSeats(List<SeatDTO> seatDTOs) {
        List<Seat> seats = seatDTOs.stream()
                .map(seatMapper::toEntity)
                .collect(Collectors.toList());
        seatRepository.deleteAll(seats);
        log.info("Места успешно удалены.");
    }

    public List<Seat> findSeatsByFlightId(Long flightID) {
        return seatRepository.findSeatsByFlightId(flightID);
    }

    public Double getSeatPriceForFlightId(Long flightID) {
        Double price = seatRepository.findMinPriceByFlightId(flightID);
        if (price == null) {
            throw new IllegalArgumentException("Нет доступных мест на рейс");
        }
        return price;
    }

    public void addSeatsToFlight(Long flightID, int seatCount, double price, String prefix) {
        if (seatCount <= 0) {
            throw new IllegalArgumentException("Количество мест должно быть положительным числом.");
        }

        List<String> existingSeatNumbers = seatRepository.findSeatNumbersByFlightID(flightID);

        List<SeatDTO> seatDTOs = new ArrayList<>();
        for (int i = 1; i <= seatCount; i++) {
            String seatNumber = prefix + i;
            if (!existingSeatNumbers.contains(seatNumber)) {
                SeatDTO seatDTO = new SeatDTO();
                seatDTO.setFlightID(flightID);
                seatDTO.setSeatNumber(seatNumber);
                seatDTO.setIsBooked(false);
                seatDTO.setPrice(price);
                seatDTOs.add(seatDTO);
            } else {
                log.warn("Место с номером {} уже существует и не будет добавлено.", seatNumber);
            }
        }

        if (!seatDTOs.isEmpty()) {
            createSeats(seatDTOs);
        } else {
            throw new IllegalArgumentException("Все номера мест уже существуют для указанного префикса.");
        }
    }

    public void addSeats(Long flightID, HttpServletRequest request) {
        try {
            addSeatsToFlight(flightID, Integer.parseInt(request.getParameter("seatCountA")),
                    Double.parseDouble(request.getParameter("seatPriceA")), "A");
            addSeatsToFlight(flightID, Integer.parseInt(request.getParameter("seatCountB")),
                    Double.parseDouble(request.getParameter("seatPriceB")), "B");
            addSeatsToFlight(flightID, Integer.parseInt(request.getParameter("seatCountC")),
                    Double.parseDouble(request.getParameter("seatPriceC")), "C");
        } catch (Exception e) {
            log.error("Ошибка при добавлении мест: ", e);
            throw new RuntimeException("Ошибка при добавлении мест.");
        }
    }

    public boolean bookSeats(Long flightId, int seatCount, Long userId) {
        return seatRepository.bookSeats(flightId, seatCount, userId);
    }
}
