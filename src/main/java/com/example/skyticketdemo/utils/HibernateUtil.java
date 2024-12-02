package com.example.skyticketdemo.utils;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

@Slf4j
@WebListener
public class HibernateUtil implements ServletContextListener {

    @Getter
    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory isn't initialized.");
        }
        return sessionFactory;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            log.info("SessionFactory initialized.");
            sessionFactory = new Configuration().configure().buildSessionFactory();
            sce.getServletContext().setAttribute("SessionFactory", sessionFactory);
        } catch (HibernateException e) {
            log.error("SessionFactory initialization failed.", e);
            throw new RuntimeException("SessionFactory initialization failed.", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (sessionFactory != null) {
            try {
                log.info("");
                sessionFactory.close();
            } catch (HibernateException e) {
            }
        } else {
            log.warn("SessionFactory is null");
        }
    }

    public static Session getSession() {
        return getSessionFactory().openSession();
    }
}
