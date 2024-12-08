package com.example.skyticketdemo.repository.interfac;

import com.example.skyticketdemo.entity.Airline;

import java.util.List;

public interface AirlineRepository {
    void save(Airline airline);
    Airline findById(Long id);
    List<Airline> findAll();
    void update(Airline airline);
    void delete(Airline airline);
}
