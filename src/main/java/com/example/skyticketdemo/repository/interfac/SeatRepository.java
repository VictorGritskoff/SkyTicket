package com.example.skyticketdemo.repository.interfac;

import com.example.skyticketdemo.entity.Seat;

import java.util.List;

public interface SeatRepository extends BaseRepository<Seat, Long> {
    void saveAll(List<Seat> seats);
    void deleteAll(List<Seat> seats);
    List<String> findSeatNumbersByFlightID(Long flightID);
}
