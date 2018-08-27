package com.saman.oak.core.business.dao;

import com.saman.oak.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 9/29/2017.
 */
public interface CompleteDao<ID extends Serializable, E extends BaseEntity<ID>>
        extends Dao<ID, E>, CrudDao<ID, E>, SearchDao<ID, E>, PaginationDao<ID, E> {
    @Override
    default void setEntityType(Class<E> c) {
        logger().debug("default method has empty body");
    }
}
