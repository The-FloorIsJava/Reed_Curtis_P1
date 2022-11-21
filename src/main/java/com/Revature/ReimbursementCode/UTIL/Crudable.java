package com.Revature.ReimbursementCode.UTIL;

import java.util.List;

public interface Crudable<T> {

    T create(T newObject);

    List<T> findAll();

    T findById(int id);

    boolean update(T updateObject);

    boolean delete(int id);
}
