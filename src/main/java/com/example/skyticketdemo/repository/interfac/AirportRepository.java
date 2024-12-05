package com.example.skyticketdemo.repository.interfac;

import com.example.skyticketdemo.entity.Airport;

import java.util.List;

public interface AirportRepository {
    void save(Airport airport);
    Airport findById(Long id);
    List<Airport> findAll();
    List<Airport> findAllByName(List<String> ids);
    void update(Airport airport);
    void delete(Airport airport);
}
