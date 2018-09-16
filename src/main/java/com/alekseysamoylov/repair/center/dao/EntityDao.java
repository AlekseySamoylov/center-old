package com.alekseysamoylov.repair.center.dao;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/5/16.
 */
public interface EntityDao<T> {
    T get(Long id);
    Long add(T entity);
    boolean edit(T entity);
    boolean delete(Long id);
    List<T> getAll();
    List<T> find(String param);
}
