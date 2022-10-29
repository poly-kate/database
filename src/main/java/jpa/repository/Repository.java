package jpa.repository;

import jpa.specification.Specification;

import java.util.List;

public interface Repository<T, PK> {
    void add(T t);
    void update(T t);
    T getByPK(PK pk);
    void delete(T t);
    void deleteByPK(PK pk);
    List<T> getBySpecification(Specification<T> specification);
}

