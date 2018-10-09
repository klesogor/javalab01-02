package app.repositories;

import app.exceptions.DomainException;
import java.util.List;

public interface Repository<T> {

    void save(T entity) throws DomainException;

    void delete(T entity) throws DomainException;

    T getById(Object id) throws DomainException;

    List<T> getAll() throws DomainException;

}
