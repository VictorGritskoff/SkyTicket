package com.example.skyticketdemo.utils;

import com.example.skyticketdemo.entity.*;
import lombok.Getter;
import lombok.experimental.UtilityClass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    @Getter
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Airline.class)
                    .addAnnotatedClass(Airport.class)
                    .addAnnotatedClass(Flight.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(Seat.class)
                    .addAnnotatedClass(Ticket.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}

