package com.example.skyticketdemo.repository.impl;

import com.example.skyticketdemo.entity.Airport;
import com.example.skyticketdemo.repository.interfac.AirportRepository;
import com.example.skyticketdemo.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class AirportRepositoryImpl implements AirportRepository {
    @Override
    public void save(Airport airport) {

    }

    @Override
    public Airport findById(Long id) {
        try (Session session = HibernateUtil.getSession()){
            return session.find(Airport.class, id);
        }
    }

    @Override
    public List<Airport> findAll() {
        return List.of();
    }

    @Override
    public void update(Airport airport) {

    }

    @Override
    public void delete(Long id) {

    }
}
