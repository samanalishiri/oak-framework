package com.saman.oak.portal.business.service;

import com.saman.oak.core.business.dao.CompleteDao;
import com.saman.oak.core.business.service.CompleteService;
import com.saman.oak.core.converter.Converter;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public abstract class ServiceProxy<ID extends Serializable, M extends BaseModel<ID>, E extends BaseEntity<ID>, D extends CompleteDao<ID, E>>
        implements CompleteService<ID, M, E, D> {

    protected CompleteService service;

    public void setService(CompleteService service) {
        this.service = service;
    }

    @Override
    public Converter<E, M> getConverter() {
        return service.getConverter();
    }

    @Override
    public D getDao() {
        return (D) service.getDao();
    }

    @Override
    public Optional<M> save(M m) {
        return service.save(m);
    }

    @Override
    public Optional<M[]> find() {
        return service.find();
    }

    @Override
    public Optional<M> find(ID key) {
        return service.find(key);
    }

    @Override
    public Optional<M[]> find(M m) {
        return service.find(m);
    }

    @Override
    public Optional<M> findOne(M m) {
        return service.findOne(m);
    }

    @Override
    public Optional<M> update(M m) {
        return service.update(m);
    }

    @Override
    public void delete(ID key) {
        service.delete(key);
    }

    @Override
    public void fastDelete(ID key) {
        service.fastDelete(key);
    }

}
