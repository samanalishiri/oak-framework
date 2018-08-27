package com.saman.oak.core.business.dao;

import com.saman.oak.core.business.Specification;
import com.saman.oak.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Optional;


public interface PaginationDao<ID extends Serializable, E extends BaseEntity<ID>> {

    Optional<E[]> pagination(int first, int max, Specification<E> specification);

    Optional<E[]> pagination(int first, int max, E e);

    Optional<E[]> pagination(int first, int max);

}
