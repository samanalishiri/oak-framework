package com.saman.oak.core.business;

import com.saman.oak.core.database.HqlKeyword;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by saman on 9/18/2017.
 */
@FunctionalInterface
public interface Specification<E> extends HqlKeyword {

    Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder cb);
}
