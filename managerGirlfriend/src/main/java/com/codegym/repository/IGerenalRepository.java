package com.codegym.repository;

import java.util.List;

public interface IGerenalRepository<E> {
    List<E>findAll();
    E findByName(String name);
    void save(E e);
    void delete(E e);
    void edit(int id, String name, float price, String image);
}
