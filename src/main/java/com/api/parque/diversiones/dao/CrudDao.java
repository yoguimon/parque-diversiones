package com.api.parque.diversiones.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudDao<T> {
    List<T> getAll();
    boolean delete(int id);
    boolean create(T t);
    T getOne(int id);
    boolean setOne(T t);
}
