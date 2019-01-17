package com.saman.oak.springbase.business;

import com.saman.oak.core.business.dao.SpringDataJpaRepository;
import com.saman.oak.core.business.hateoas.service.ResourceableCompleteService;
import com.saman.oak.core.converter.Converter;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;
import com.saman.oak.core.naming.BusinessServiceConstant;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@Service(BusinessServiceConstant.RESOURCEABLE_SPRING_SERVICE)
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ResourceableSpringService<ID extends Serializable,
        M extends BaseModel<ID>,
        E extends BaseEntity<ID>,
        R extends Resource<E>,
        D extends SpringDataJpaRepository<ID, E>>
        implements ResourceableCompleteService<ID, M, E, R, D> {

    private D repository;

    private Converter<E, M> converter;

    private ResourceAssemblerSupport<E, R> assembler;

    private Consumer<M>[] beforeSaveRules;

    @Override
    public D getRepository() {
        return repository;
    }

    @Override
    public void setRepository(D repository) {
        this.repository = repository;
    }

    @Override
    public Converter<E, M> getConverter() {
        return converter;
    }

    @Override
    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    @Override
    public ResourceAssemblerSupport<E, R> getAssembler() {
        return assembler;
    }

    @Override
    public void setAssembler(ResourceAssemblerSupport<E, R> assembler) {
        this.assembler = assembler;
    }

    @Override
    public void beforeSaveRules(Consumer<M>... rules) {
        this.beforeSaveRules = rules;
    }

    @Override
    public Optional<ID> save(M m) {
        Arrays.stream(beforeSaveRules).forEach(c -> c.accept(m));
        return Optional.of(repository.save(converter.convert(m)).getId());
    }

    @Override
    public Optional<R[]> find() {
        return null;
    }

    @Override
    public Optional<R> find(ID key) {
        return Optional.of(assembler.toResource(repository.findById(key).get()));
    }

    @Override
    public Optional<R[]> find(M m) {
        return null;
    }

    @Override
    public Optional<R> findOne(M m) {
        return null;
    }

    @Override
    public void edit(M m) {
        repository.save(converter.convert(m));
    }

    @Override
    public void delete(ID key) {
        repository.deleteById(key);
    }
}
