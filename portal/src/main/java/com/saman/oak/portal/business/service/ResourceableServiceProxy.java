package com.saman.oak.portal.business.service;

import com.saman.oak.core.business.dao.SpringDataJpaRepository;
import com.saman.oak.core.business.hateoas.service.ResourceableCompleteService;
import com.saman.oak.core.converter.Converter;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public abstract class ResourceableServiceProxy<ID extends Serializable,
        M extends BaseModel<ID>,
        E extends BaseEntity<ID>,
        R extends Resource<E>,
        D extends SpringDataJpaRepository<ID, E>>
        implements ResourceableCompleteService<ID, M, E, R, D> {

    protected ResourceableCompleteService<ID, M, E, R, D> service;

    @Required
    public void setService(ResourceableCompleteService<ID, M, E, R, D> service) {
        this.service = service;
    }

    @Override
    public D getRepository() {
        return (D) service.getRepository();
    }

    @Override
    public void setRepository(D repository) {
        service.setRepository(repository);
    }

    @Override
    public Converter<E, M> getConverter() {
        return service.getConverter();
    }

    @Override
    public void setConverter(Converter<E, M> converter) {
        service.setConverter(converter);
    }

    @Override
    public ResourceAssemblerSupport<E, R> getAssembler() {
        return service.getAssembler();
    }

    @Override
    public void setAssembler(ResourceAssemblerSupport<E, R> assembler) {
        service.setAssembler(assembler);
    }

    @Override
    public void beforeSaveRules(Consumer<M>... rules) {
        service.beforeSaveRules(rules);
    }

    @Override
    public Optional<ID> save(M m) {
        return service.save(m);
    }

    @Override
    public Optional<R[]> find() {
        return service.find();
    }

    @Override
    public Optional<R> find(ID key) {
        return service.find(key);
    }

    @Override
    public Optional<R[]> find(M m) {
        return service.find(m);
    }

    @Override
    public Optional<R> findOne(M m) {
        return service.findOne(m);
    }

    @Override
    public void edit(M m) {
        service.edit(m);
    }

    @Override
    public void delete(ID key) {
        service.delete(key);
    }

}
