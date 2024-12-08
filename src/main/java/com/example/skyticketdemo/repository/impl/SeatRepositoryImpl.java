package com.example.skyticketdemo.repository.impl;

import com.example.skyticketdemo.entity.Seat;
import com.example.skyticketdemo.repository.interfac.SeatRepository;
import com.example.skyticketdemo.utils.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Slf4j
public class SeatRepositoryImpl implements SeatRepository {
    @Override
    public void saveAll(List<Seat> seats) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            for (int i = 0; i < seats.size(); i++) {
                session.persist(seats.get(i));
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка при сохранении мест: ", e);
        }
    }

    @Override
    public List<String> findSeatNumbersByFlightID(Long flightID) {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("SELECT s.seatNumber FROM Seat s WHERE s.flight.flightID = :flightID", String.class)
                    .setParameter("flightID", flightID)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public void deleteAll(List<Seat> seats) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            for (int i = 0; i < seats.size(); i++) {
                session.remove(seats.get(i));
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка при удалении мест: ", e);
        }
    }


    @Override
    public void save(Seat entity) {
        log.warn("Вызов метода save в неподдерживаемом репозитории. Объект: {}", entity);
    }

    @Override
    public Seat findById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Seat.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Seat> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Seat", Seat.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public void update(Seat seat) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(seat);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Seat entity) {
        log.warn("Вызов метода delete в неподдерживаемом репозитории. Объект: {}", entity);
    }
}
