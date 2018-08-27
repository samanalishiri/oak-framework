package com.saman.oak.core.business.service;

import com.saman.oak.core.business.dao.CompleteDao;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;

import java.io.Serializable;
import java.util.Optional;


public interface CrudService<ID extends Serializable, M extends BaseModel<ID>, E extends BaseEntity<ID>, D extends CompleteDao<ID, E>> {

    Optional<M> save(M m);

    Optional<M> update(M m);

    Optional<M[]> find();

    Optional<M> find(ID key);

    void delete(ID key);

    void fastDelete(ID key);
}
