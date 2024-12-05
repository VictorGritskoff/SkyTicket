package com.example.skyticketdemo.repository.impl;

import com.example.skyticketdemo.entity.Airport;
import com.example.skyticketdemo.repository.interfac.AirportRepository;
import com.example.skyticketdemo.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AirportRepositoryImpl implements AirportRepository {

    @Override
    public void save(Airport airport) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.persist(airport);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка сохранения аэропорта: " + airport, e);
        }
    }

    @Override
    public Airport findById(Long id) {
        try (Session session = HibernateUtil.getSession()){
            return session.find(Airport.class, id);
        }
    }

    @Override
    public List<Airport> findAll() {
        try (Session session = HibernateUtil.getSession()){
            return session.createQuery("FROM Airport", Airport.class).getResultList(); // Возможна ошибка
        }
    }

    @Override
    public List<Airport> findAllByName(List<String> names) {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Airport a WHERE a.airportName IN :names", Airport.class)
                    .setParameter("names", names)
                    .list();
        }
    }

    @Override
    public void update(Airport airport) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.merge(airport);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка обновления данных аэропорта: " + airport, e);
        }
    }

    @Override
    public void delete(Airport airport) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.remove(session.contains(airport) ? airport : session.merge(airport));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка удаления аэропорта: " + airport, e);
        }
    }
}
