package com.saman.oak.core.business.hateoas.service;

import com.saman.oak.core.business.dao.SpringDataJpaDao;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;
import org.springframework.hateoas.Resource;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public interface ResourceableSearchService<ID extends Serializable, M extends BaseModel<ID>, E extends BaseEntity<ID>, R extends Resource<E>, D extends SpringDataJpaDao<ID, E>> {
    Optional<R[]> find(M m);

    Optional<R> findUnique(M m);
}
