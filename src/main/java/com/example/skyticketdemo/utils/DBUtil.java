package com.example.skyticketdemo.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class DBUtil {

    public static EntityManagerFactory createEntityManagerFactory() {
        Map<String, String> properties = new HashMap<>();

        String dbDriver = System.getenv("DB_DRIVER");
        String dbUrl = System.getenv("DB_URL");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        if (dbDriver == null || dbUrl == null || dbUsername == null || dbPassword == null) {
            throw new IllegalStateException("Не заданы системные переменные для подключения к БД");
        }

        properties.put("javax.persistence.jdbc.driver", dbDriver);
        properties.put("javax.persistence.jdbc.url", dbUrl);
        properties.put("javax.persistence.jdbc.user", dbUsername);
        properties.put("javax.persistence.jdbc.password", dbPassword);

        return Persistence.createEntityManagerFactory("myPersistenceUnit", properties);
    }
}