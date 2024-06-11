package com.cbfacademy.apiassessment.core;

import java.io.Serializable;
import java.util.List;

public interface apiRepository<T, ID extends Serializable> {

    List<T> retrieveAll() throws PersistenceException;

    T retrieve(ID id) throws IllegalArgumentException, PersistenceException;

    T create(T entity) throws IllegalArgumentException, PersistenceException;

    void delete(T entity) throws IllegalArgumentException, PersistenceException;

    T update(T entity) throws IllegalArgumentException, PersistenceException;
}
