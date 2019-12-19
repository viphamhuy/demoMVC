package com.codegym.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IGeneralService<E> {

    List<E> findAllHaveBusiness();
    E findById(Long id);
    E findByName(String name);
    void save(E e);
    void delete(E e);

}
