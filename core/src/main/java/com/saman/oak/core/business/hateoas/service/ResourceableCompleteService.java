package com.saman.oak.core.business.hateoas.service;

import com.saman.oak.core.business.dao.SpringDataJpaDao;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;
import org.springframework.hateoas.Resource;

import java.io.Serializable;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public interface ResourceableCompleteService<ID extends Serializable, M extends BaseModel<ID>, E extends BaseEntity<ID>, R extends Resource<E>, D extends SpringDataJpaDao<ID, E>>
        extends ResourceableService<ID, M, E, R, D>, ResourceableCrudService<ID, M, E, R, D>, ResourceableSearchService<ID, M, E, R, D> {

    @Override
    default void setEntityType(Class<? extends BaseEntity> c) {
        logger().debug("default method has empty body");
    }

    @Override
    default void setModelType(Class<? extends BaseModel> c) {
        logger().debug("default method has empty body");
    }

    @Override
    default void setDao(D d) {
        logger().debug("default method has empty body");
    }

}
