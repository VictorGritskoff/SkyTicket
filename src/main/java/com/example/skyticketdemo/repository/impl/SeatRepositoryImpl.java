package com.example.skyticketdemo.repository.impl;

import com.example.skyticketdemo.entity.Flight;
import com.example.skyticketdemo.entity.Seat;
import com.example.skyticketdemo.entity.Ticket;
import com.example.skyticketdemo.entity.User;
import com.example.skyticketdemo.repository.interfac.SeatRepository;
import com.example.skyticketdemo.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
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

    public Double findMinPriceByFlightId(Long flightID) {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery(
                            "SELECT MIN(s.price) FROM Seat s WHERE s.flight.flightID = :flightID", Double.class)
                    .setParameter("flightID", flightID)
                    .uniqueResult();
        } catch (Exception e) {
            return null;
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

    public List<Seat> findSeatsByFlightId(Long flightID) {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Seat s WHERE s.flight.flightID = :flightID", Seat.class)
                    .setParameter("flightID", flightID)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public boolean bookSeats(Long flightId, int seatCount, Long userId) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            try {
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<Seat> query = cb.createQuery(Seat.class);
                Root<Seat> seatRoot = query.from(Seat.class);

                query.select(seatRoot)
                        .where(
                                cb.equal(seatRoot.get("flight").get("flightID"), flightId),
                                cb.equal(seatRoot.get("isBooked"), false)
                        );

                List<Seat> availableSeats = session.createQuery(query).getResultList();

                if (availableSeats.size() < seatCount) {
                    tx.rollback();
                    return false;
                }

                Flight flight = session.get(Flight.class, flightId);
                User user = session.get(User.class, userId);

                if (flight == null || user == null) {
                    tx.rollback();
                    return false;
                }

                for (int i = 0; i < seatCount; i++) {
                    Seat seat = availableSeats.get(i);
                    seat.setIsBooked(true);
                    session.update(seat);

                    Ticket ticket = new Ticket();
                    ticket.setUser(user);
                    ticket.setFlight(flight);
                    ticket.setSeat(seat);
                    ticket.setAmount(seat.getPrice());
                    ticket.setBookingDate(LocalDateTime.now());

                    session.persist(ticket);
                }

                tx.commit();
                return true;
            } catch (Exception e) {
                tx.rollback();
                e.printStackTrace();
                return false;
            }
        }
    }

}
