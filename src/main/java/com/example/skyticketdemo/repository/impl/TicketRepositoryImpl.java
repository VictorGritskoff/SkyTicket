package com.example.skyticketdemo.repository.impl;

import com.example.skyticketdemo.entity.Ticket;
import com.example.skyticketdemo.repository.interfac.BaseRepository;
import com.example.skyticketdemo.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class TicketRepositoryImpl implements BaseRepository<Ticket, Long> {
    @Override
    public void save(Ticket entity) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ticket findById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Ticket.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Ticket> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Ticket", Ticket.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public void update(Ticket entity) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Ticket entity) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> findByUser_UserID(Long userID){
        try (Session session = HibernateUtil.getSession()) {
            Query<Ticket> query = session.createQuery("FROM Ticket t WHERE t.user.userID = :userID", Ticket.class);
            query.setParameter("userID", userID);
            return query.getResultList();
        }
    }
    public List<LocalDateTime> findDepartureTimesByUser(Long userID) {
        try (Session session = HibernateUtil.getSession()) {
            Query<LocalDateTime> query = session.createQuery(
                    "SELECT f.departureTime FROM Ticket t JOIN t.flight f WHERE t.user.userID = :userID",
                    LocalDateTime.class
            );
            query.setParameter("userID", userID);
            return query.getResultList();
        }
    }
}
