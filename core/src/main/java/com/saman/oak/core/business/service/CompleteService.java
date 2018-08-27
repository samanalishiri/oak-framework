package com.saman.oak.core.business.service;

import com.saman.oak.core.business.dao.CompleteDao;
import com.saman.oak.core.converter.Converter;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;

import java.io.Serializable;

/**
 * Created by Administrator on 9/29/2017.
 */
public interface CompleteService<ID extends Serializable, M extends BaseModel<ID>, E extends BaseEntity<ID>, D extends CompleteDao<ID, E>>
        extends Service<ID, M, E, D>, CrudService<ID, M, E, D>, SearchService<ID, M, E, D> {
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

    @Override
    default void setConverter(Converter<E, M> converter) {
        logger().debug("default method has empty body");
    }


}
