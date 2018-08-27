package com.saman.oak.core.business.service;

import com.saman.oak.core.business.dao.CompleteDao;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by saman on 11/27/2017.
 */
public interface SearchService<ID extends Serializable, M extends BaseModel<ID>, E extends BaseEntity<ID>, D extends CompleteDao<ID, E>> {
    Optional<M[]> find(M m);

    Optional<M> findUnique(M m);
}
