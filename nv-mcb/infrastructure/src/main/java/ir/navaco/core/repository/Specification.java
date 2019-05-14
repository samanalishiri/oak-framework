package ir.navaco.core.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface Specification<E> {

    Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder cb);
}
