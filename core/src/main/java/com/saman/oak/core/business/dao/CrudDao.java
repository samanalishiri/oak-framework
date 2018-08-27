package com.saman.oak.core.business.dao;

import com.saman.oak.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Optional;


public interface CrudDao<ID extends Serializable, E extends BaseEntity<ID>> {

    Optional<E> save(E e);

    Optional<E> update(E e);

    Optional<E[]> find();

    Optional<E> find(ID key);

    void delete(ID key);

    void fastDelete(ID key);
}
