package ir.navaco.core.repository;

import ir.navaco.core.domain.AbstractEntity;
import ir.navaco.core.domain.SearchRequest;
import ir.navaco.core.domain.SearchResponse;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
public abstract class AbstractRepositoryImpl<ID extends Serializable, T extends AbstractEntity<ID>> implements AbstractRepository<ID, T> {
    private final Class<T> clazz;

    @Inject
    protected GenericRepository genericRepository;

    @SuppressWarnings("unchecked")
    public AbstractRepositoryImpl() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public AbstractRepositoryImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected EntityManager getEntityManager() {
        return genericRepository.getEntityManager();
    }

    @Override
    public long countAll() {
        return genericRepository.countAll(clazz);
    }

    @Override
    public long countByNamedQuery(String namedQuery) {
        return genericRepository.countByNamedQuery(clazz, namedQuery);
    }

    @Override
    public long countByNamedQuery(String namedQuery, Map<String, Object> params) {
        return genericRepository.countByNamedQuery(clazz, namedQuery, params);
    }

    @Override
    public List<T> findAll(SearchRequest request) {
        return genericRepository.findAll(clazz, request);
    }

    @Override
    public List<T> findByNamedQuery(String namedQuery, SearchRequest request) {
        return genericRepository.findByNamedQuery(clazz, namedQuery, request);
    }

    @Override
    public List<T> findByNamedQuery(String namedQuery, Map<String, Object> params, SearchRequest request) {
        return genericRepository.findByNamedQuery(clazz, namedQuery, params, request);
    }

    @Override
    public SearchResponse<T> searchAll(SearchRequest request) {
        return genericRepository.searchAll(clazz, request);
    }

    @Override
    public SearchResponse<T> searchByNamedQuery(String namedQuery, SearchRequest request) {
        return genericRepository.searchByNamedQuery(clazz, namedQuery, request);
    }

    @Override
    public SearchResponse<T> searchByNamedQuery(String namedQuery, Map<String, Object> params, SearchRequest request) {
        return genericRepository.searchByNamedQuery(clazz, namedQuery, params, request);
    }

    @Override
    public Optional<T> getReference(ID id) {
        return genericRepository.getReference(clazz, id);
    }

    @Override
    public Optional<T> findOne(ID id) {
        return genericRepository.findOne(clazz, id);
    }

    @Override
    public Optional<T> findOne(String query, Map<String, Object> params) {
        return genericRepository.findOne(clazz, query, params);
    }

    @Override
    public Optional<T> persist(T entity) {
        return Optional.ofNullable(genericRepository.persist(clazz, entity).get());
    }

    @Override
    public Optional<T> merge(T entity) {
        return genericRepository.merge(clazz, entity);
    }

    @Override
    public Optional<T> delete(T entity) {
        return genericRepository.delete(clazz, entity);
    }

    @Override
    public Optional<T> delete(ID entityId) {
        return genericRepository.delete(clazz, entityId);
    }
}