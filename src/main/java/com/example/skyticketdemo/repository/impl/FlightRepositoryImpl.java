package com.example.skyticketdemo.repository.impl;

import com.example.skyticketdemo.entity.Flight;
import com.example.skyticketdemo.entity.Seat;
import com.example.skyticketdemo.repository.interfac.BaseRepository;
import com.example.skyticketdemo.utils.HibernateUtil;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightRepositoryImpl implements BaseRepository<Flight, Long> {

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

    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDate departureDate, Integer tickets) {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Flight> query = cb.createQuery(Flight.class);
            Root<Flight> flightRoot = query.from(Flight.class);

            List<Predicate> predicates = new ArrayList<>();

            if (departureCity != null && !departureCity.isEmpty()) {
                predicates.add(cb.equal(flightRoot.get("departureAirport").get("city"), departureCity));
            }

            if (arrivalCity != null && !arrivalCity.isEmpty()) {
                predicates.add(cb.equal(flightRoot.get("arrivalAirport").get("city"), arrivalCity));
            }

            if (departureDate != null) {
                predicates.add(cb.equal(cb.function("DATE", LocalDate.class, flightRoot.get("departureTime")), departureDate));
            }

            if (tickets != null && tickets > 0) {
                Subquery<Long> availableSeats = query.subquery(Long.class);
                Root<Seat> seatRoot = availableSeats.from(Seat.class);
                availableSeats.select(cb.count(seatRoot))
                        .where(
                                cb.equal(seatRoot.get("flight").get("flightID"), flightRoot.get("flightID")),
                                cb.equal(seatRoot.get("isBooked"), false)
                        )
                        .groupBy(seatRoot.get("flight"));

                predicates.add(cb.greaterThanOrEqualTo(availableSeats, tickets.longValue()));
            }

            query.select(flightRoot).where(predicates.toArray(new Predicate[0]));

            return session.createQuery(query).getResultList();
        }
    }
}
