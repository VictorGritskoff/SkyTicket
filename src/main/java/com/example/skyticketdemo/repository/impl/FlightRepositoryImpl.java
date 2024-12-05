package com.example.skyticketdemo.repository.impl;

import com.example.skyticketdemo.entity.Flight;
import com.example.skyticketdemo.repository.interfac.FlightRepository;
import com.example.skyticketdemo.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FlightRepositoryImpl implements FlightRepository {

    @Override
    public void save(Flight flight) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.persist(flight);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка сохранения рейса: " + flight, e);
        }
    }

    @Override
    public Flight findById(Long id) {
        try (Session session = HibernateUtil.getSession()){
            return session.find(Flight.class, id);
        }
    }

    @Override
    public List<Flight> findAll() {
        try (Session session = HibernateUtil.getSession()){
            return session.createQuery("FROM Flight", Flight.class).getResultList();
        }
    }

    @Override
    public void update(Flight flight) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.merge(flight);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка обновления данных рейса: " + flight, e);
        }
    }

    @Override
    public void delete(Flight flight) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.remove(session.contains(flight) ? flight : session.merge(flight));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка удаления рейса: " + flight, e);
        }
    }
}
