package com.example.democlientes.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    T update(T t);

    T updatePartial(T t);

    void delete(Long id);

}