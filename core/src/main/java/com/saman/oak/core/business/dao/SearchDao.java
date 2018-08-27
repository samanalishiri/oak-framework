package com.saman.oak.core.business.dao;

import com.saman.oak.core.business.Specification;
import com.saman.oak.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Optional;


public interface SearchDao<ID extends Serializable, E extends BaseEntity<ID>> {

    Optional<E> findUnique(Specification<E> specification);

    Optional<E[]> find(Specification<E> specification);

    Optional<E[]> findByField(String field, Object value);

    Optional<E> findUniqueByField(String field, Object value);
}
