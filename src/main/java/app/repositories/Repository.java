package app.repositories;

import app.exceptions.DomainException;

import java.util.List;

public interface Repository<T> {

    void save(T entity) throws Exception;

    void delete(T entity) throws Exception;

    T getById(Object id) throws Exception;

    List<T> getAll() throws Exception;

}
