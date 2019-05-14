package ir.navaco.core.service;

import ir.navaco.core.config.init.ApplicationContextBean;
import ir.navaco.core.domain.AbstractEntity;
import ir.navaco.core.domain.EmptyEntity;
import ir.navaco.core.domain.SearchRequest;
import ir.navaco.core.exception.NotSameVersionException;
import ir.navaco.core.repository.AbstractRepository;
import ir.navaco.core.util.GenericUtils;
import ir.navaco.core.util.Transformer;
import ir.navaco.core.vo.AbstractVo;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ir.navaco.core.util.Transformer.FIRST_DEEP;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@Transactional
public abstract class AbstractBusinessService<ID extends Serializable,
        V extends AbstractVo<ID>,
        E extends AbstractEntity<ID>,
        R extends AbstractRepository<ID, E>>
        implements CrudService<ID, V>, SearchService<ID, V> {

    private final Class<V> valueObjectType = (Class<V>) GenericUtils.extract(this.getClass(), 1);

    private final Class<E> entityType = (Class<E>) GenericUtils.extract(this.getClass(), 2);

    protected R repository;

    protected Transformer<ID, E, V> transformer;

    public AbstractBusinessService(R repository, Transformer<ID, E, V> transformer) {
        this.repository = repository;
        this.transformer = transformer;
    }

    @Override
    public Optional<ID> save(V v) {
        ID id = repository.persist(transformer.transform(v))
                .orElseGet(ApplicationContextBean.getBeanAsSupplier(EmptyEntity.NAME, entityType))
                .getId();

        return Optional.ofNullable(id);
    }

    @Override
    public Optional<V> findById(ID id) {
        V v = transformer.transform(repository.findOne(id)
                .orElseGet(ApplicationContextBean.getBeanAsSupplier(EmptyEntity.NAME, entityType)));

        return Optional.ofNullable(v);
    }

    @Override
    public void update(V v) {
        E e = repository.findOne(v.getId())
                .orElseGet(ApplicationContextBean.getBeanAsSupplier(EmptyEntity.NAME, entityType));

        if (!Objects.equals(v.getVersion(), e.getVersion())) {
            throw new NotSameVersionException();
        }

        transformer.transform(v, e);
    }

    @Override
    public void delete(ID id) {
        repository.delete(id);
    }

    @Override
    public Optional<List<V>> findAll() {
        List<V> valuesObject = transformer.transformEntitiesToModels(
                () -> repository.findAll(SearchRequest.UNLIMITED)
                , FIRST_DEEP);

        return Optional.ofNullable(valuesObject);
    }

}
