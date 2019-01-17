package com.saman.oak.core.business.hateoas.service;

import com.saman.oak.core.business.dao.SpringDataJpaRepository;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;
import org.springframework.hateoas.Resource;

import java.io.Serializable;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public interface ResourceableCompleteService<ID extends Serializable,
        M extends BaseModel<ID>,
        E extends BaseEntity<ID>,
        R extends Resource<E>,
        D extends SpringDataJpaRepository<ID, E>> extends ResourceableService<ID, M, E, R, D>,
        ResourceableCrudService<ID, M, E, R, D>,
        ResourceableSearchService<ID, M, E, R, D> {
}
