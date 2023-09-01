package com.example.democlientes.converter;

import java.util.List;
import java.util.stream.Collectors;

public interface Converter<E, D> {

    D fromEntity(E entity);

    E fromDTO(D dto);

    default List<D> fromEntity(List<E> entityList) {
        return entityList.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    default List<E> fromDTO(List<D> dtoList) {
        return dtoList.stream()
                .map(this::fromDTO)
                .collect(Collectors.toList());
    }

}
