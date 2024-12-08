package com.example.skyticketdemo.repository.impl;

import com.example.skyticketdemo.entity.Airline;
import com.example.skyticketdemo.repository.interfac.AirlineRepository;
import com.example.skyticketdemo.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AirlineRepositoryImpl implements AirlineRepository {

    @Override
    public void save(Airline airline) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(airline);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Airline findById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Airline.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Airline> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Airline", Airline.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public void update(Airline airline) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(airline);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Airline airline) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(airline);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
