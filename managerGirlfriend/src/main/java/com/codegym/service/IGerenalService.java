package com.codegym.service;

import java.util.List;

public interface IGerenalService<E> {
    List<E>findAllHaveBusiness();
    E findByName(String name);
    void save(E e);
    void delete(E e);
    void edit(int id, String name, float price, String image);
}
