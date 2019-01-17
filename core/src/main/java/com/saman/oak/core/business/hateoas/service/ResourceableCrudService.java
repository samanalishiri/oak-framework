package com.saman.oak.core.business.hateoas.service;

import com.saman.oak.core.business.dao.SpringDataJpaRepository;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;
import org.springframework.hateoas.Resource;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public interface ResourceableCrudService<ID extends Serializable,
        M extends BaseModel<ID>,
        E extends BaseEntity<ID>,
        R extends Resource<E>,
        D extends SpringDataJpaRepository<ID, E>> {

    void beforeSaveRules(Consumer<M>... rules);

    Optional<ID> save(M m);

    void edit(M m);

    Optional<R[]> find();

    Optional<R> find(ID key);

    void delete(ID key);
}
