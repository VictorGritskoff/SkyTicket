package com.example.skyticketdemo.repository.interfac;

import com.example.skyticketdemo.entity.Seat;

import java.util.List;

public interface SeatRepository {
    void saveAll(List<Seat> seats);
    Seat findById(Long id);
    List<Seat> findAll();
    void update(Seat seat);
    void deleteAll(List<Seat> seats);
    List<String> findSeatNumbersByFlightID(Long flightID);
}
