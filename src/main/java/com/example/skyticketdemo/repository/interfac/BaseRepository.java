package com.example.skyticketdemo.repository.interfac;

import java.util.List;

public interface BaseRepository<T, ID> {
    void save(T entity);
    T findById(ID id);
    List<T> findAll();
    void update(T entity);
    void delete(T entity);
}

