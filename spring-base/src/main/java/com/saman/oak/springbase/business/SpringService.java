package com.saman.oak.springbase.business;

import com.saman.oak.core.business.dao.CompleteDao;
import com.saman.oak.core.business.service.CompleteService;
import com.saman.oak.core.converter.Converter;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;
import com.saman.oak.core.naming.BusinessServiceConstant;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by Administrator on 9/29/2017.
 */
@Service(BusinessServiceConstant.SPRING_SERVICE)
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SpringService<ID extends Serializable, M extends BaseModel<ID>, E extends BaseEntity<ID>, D extends CompleteDao<ID, E>>
        implements CompleteService<ID, M, E, D> {

    private Class<? extends BaseEntity> entity;
    private Class<? extends BaseModel> model;

    private D dao;

    private Converter<E, M> converter;

    @Override
    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    @Override
    public Converter<E, M> getConverter() {
        return converter;
    }

    @Override
    public void setEntityType(Class<? extends BaseEntity> c) {
        entity = c;
    }

    @Override
    public void setModelType(Class<? extends BaseModel> c) {
        model = c;
    }

    @Override
    public void setDao(D dao) {
        this.dao = dao;
    }

    @Override
    public D getDao() {
        return dao;
    }

    @Override
    public Optional<M> save(M m) {
        return Optional.of(converter.convert(dao.save(converter.convert(m)).get(), 0));
    }

    @Override
    public Optional<M[]> find() {
        return Optional.of(converter.convert(dao.find().get(), 0));
    }

    @Override
    public Optional<M> find(ID key) {
        return Optional.of(converter.convert(dao.find(key).get(), 0));
    }

    @Override
    public Optional<M[]> find(M m) {
        return null;
    }

    @Override
    public Optional<M> findUnique(M m) {
        return null;
    }

    @Override
    public Optional<M> update(M m) {
        return Optional.of(converter.convert(dao.update(converter.convert(m)).get(), 0));
    }

    @Override
    public void delete(ID key) {
        dao.delete(key);
    }

    @Override
    public void fastDelete(ID key) {
        dao.fastDelete(key);
    }
}
