package ir.navaco.core.repository;

import ir.navaco.core.domain.AbstractEntity;
import ir.navaco.core.domain.SearchRequest;
import ir.navaco.core.domain.SearchResponse;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory;
import org.hibernate.hql.spi.ParameterTranslations;
import org.hibernate.hql.spi.QueryTranslator;
import org.hibernate.hql.spi.QueryTranslatorFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static ir.navaco.core.util.ListUtil.safeList;
import static java.util.Collections.EMPTY_MAP;

@Singleton
@Repository
@Transactional
public class GenericRepositoryImpl implements GenericRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> long countAll(Class<T> clazz) {
        return count(getFindAllQuery(clazz));
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> long countByNamedQuery(Class<T> clazz, String namedQuery) {
        return countByNamedQuery(clazz, namedQuery, Collections.emptyMap());
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> long countByNamedQuery(Class<T> clazz, String namedQuery, Map<String, Object> params) {
        return count(toTypedQuery(clazz, namedQuery, params));
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> List<T> findAll(Class<T> clazz, SearchRequest request) {
        return findAll(clazz, request, false);
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> List<T> findAll(Class<T> clazz, SearchRequest request, Boolean isCache) {
        return isCache ? findByNamedQueryCache(getFindAllQuery(clazz), request) : findByNamedQuery(getFindAllQuery(clazz), request);
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> List<T> findByNamedQuery(Class<T> clazz, String namedQuery, SearchRequest request) {
        return findByNamedQuery(clazz, namedQuery, Collections.emptyMap(), request);
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> List<T> findByNamedQuery(Class<T> clazz, String namedQuery, Map<String, Object> params, SearchRequest request) {
        return findByNamedQuery(toTypedQuery(clazz, namedQuery, params), request);
    }

    @Override
    public <T> List<T> findByNamedQueryBatch(Class<T> clazz, String namedQuery, Map<String, Object> params, SearchRequest request) {
        return findByNamedQueryBatch(toTypedQueryBatch(clazz, namedQuery, params), request);
    }

    @Override
    public <T> List<T> findByNamedQueryTemporalType(Class<T> clazz, String namedQuery, Map<String, AbstractMap.SimpleImmutableEntry<Object, Object>> params, SearchRequest request) {
        return findByNamedQueryBatch(toTypedQueryTemporalType(clazz, namedQuery, params), request);
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> SearchResponse<T>
    searchAll(Class<T> clazz, SearchRequest request) {
        return searchByNamedQuery(clazz, () -> getFindAllQuery(clazz), request);
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> SearchResponse<T>
    searchByNamedQuery(Class<T> clazz, String namedQuery, SearchRequest request) {
        return searchByNamedQuery(clazz, namedQuery, Collections.emptyMap(), request);
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> SearchResponse<T>
    searchByNamedQuery(Class<T> clazz, String namedQuery, Map<String, Object> params, SearchRequest request) {
        return searchByNamedQuery(clazz, () -> toTypedQuery(clazz, namedQuery, params), request);
    }

    private <ID extends Serializable, T extends AbstractEntity<ID>> SearchResponse<T> searchByNamedQuery(Class<T> clazz, Supplier<TypedQuery<T>> typedQuerySupplier, SearchRequest request) {
        long total = count(typedQuerySupplier.get());
        List<T> list = findByNamedQuery(typedQuerySupplier.get(), request);
        return new SearchResponse<>(total, list);
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> Optional<T> getReference(Class<T> clazz, ID id) {
        return Optional.ofNullable(getEntityManager().getReference(clazz, id));
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> Optional<T> findOne(Class<T> clazz, ID id) {
        return Optional.ofNullable(getEntityManager().find(clazz, id));
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> Optional<T> findOne(Class<T> clazz, String query, Map<String, Object> params) {
        return Optional.ofNullable(toTypedQuery(clazz, query, params).getSingleResult());
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> Optional<T> persist(Class<T> clazz, T entity) {
        getEntityManager().persist(entity);
        return Optional.ofNullable(entity);
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> Optional<T> merge(Class<T> clazz, T entity) {
        return Optional.ofNullable(getEntityManager().merge(entity));
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> Optional<T> delete(Class<T> clazz, T entity) {
        getEntityManager().remove(entity);
        return Optional.ofNullable(entity);
    }

    @Override
    public <ID extends Serializable, T extends AbstractEntity<ID>> Optional<T> delete(Class<T> clazz, ID entityId) {
        Optional<T> reference = getReference(clazz, entityId);
        delete(clazz, reference.get());
        return reference;
    }

    private <ID extends Serializable, T extends AbstractEntity<ID>> TypedQuery<T>
    toTypedQuery(Class<T> clazz, String query, Map<String, Object> params) {
        TypedQuery<T> namedQuery = getEntityManager().createNamedQuery(query, clazz);
        params.forEach(namedQuery::setParameter);
        return namedQuery;
    }

    private <T> TypedQuery<T> toTypedQueryBatch(Class<T> clazz, String query, Map<String, Object> params) {
        TypedQuery<T> namedQuery = getEntityManager().createNamedQuery(query, clazz);
        params.forEach(namedQuery::setParameter);
        return namedQuery;
    }

    private <T> TypedQuery<T>
    toTypedQueryTemporalType(Class<T> clazz,
                             String query,
                             Map<String, AbstractMap.SimpleImmutableEntry<Object, Object>> params) {
        TypedQuery<T> namedQuery = getEntityManager().createNamedQuery(query, clazz);
        for (Map.Entry<String, AbstractMap.SimpleImmutableEntry<Object, Object>> mapEntry : params.entrySet()) {
            AbstractMap.SimpleImmutableEntry<Object, Object> map = mapEntry.getValue();
            if (map.getValue() == null)
                namedQuery.setParameter(mapEntry.getKey(), map.getValue());
            else
                namedQuery.setParameter(mapEntry.getKey(), (Date) map.getKey(), (TemporalType) map.getValue());
        }
        return namedQuery;
    }

    private <ID extends Serializable, T extends AbstractEntity<ID>> TypedQuery<T>
    getFindAllQuery(Class<T> clazz) {
        return getEntityManager().createQuery("from " + clazz.getName(), clazz);
    }

    private <T> List<T> findByNamedQueryBatch(TypedQuery<T> typedQuery, SearchRequest request) {
        typedQuery.setFirstResult(request.getOffset()).setMaxResults(request.getLimit());
        return safeList(typedQuery.getResultList());
    }

    private <ID extends Serializable, T extends AbstractEntity<ID>> List<T> findByNamedQuery(TypedQuery<T> typedQuery, SearchRequest request) {
        typedQuery.setFirstResult(request.getOffset()).setMaxResults(request.getLimit());
        return safeList(typedQuery.getResultList());
    }

    private <ID extends Serializable, T extends AbstractEntity<ID>> List<T> findByNamedQueryCache(TypedQuery<T> typedQuery, SearchRequest request) {
        typedQuery.setFirstResult(request.getOffset()).setMaxResults(request.getLimit());
        return safeList(typedQuery.setHint("org.hibernate.cacheable", Boolean.TRUE).getResultList());
    }

    private <ID extends Serializable, T extends AbstractEntity<ID>> long count(TypedQuery<T> namedQuery) {
        String hqlQueryText = namedQuery.unwrap(Query.class).getQueryString();
        QueryTranslatorFactory translatorFactory = new ASTQueryTranslatorFactory();

        QueryTranslator translator = translatorFactory.createQueryTranslator(
                hqlQueryText, hqlQueryText,
                EMPTY_MAP,
                (SessionFactoryImplementor) getEntityManager().getEntityManagerFactory().unwrap(SessionFactory.class),
                null
        );

        translator.compile(EMPTY_MAP, false);


        javax.persistence.Query nativeQuery = getEntityManager().createNativeQuery(
                "select count(*) from (" + translator.getSQLString() + ") x"
        );

        ParameterTranslations parameterTranslations = translator.getParameterTranslations();

        for (Parameter<?> parameter : namedQuery.getParameters()) {
            String name = parameter.getName();
            for (int position : parameterTranslations.getNamedParameterSqlLocations(name)) {
                nativeQuery.setParameter(
                        position + 1,
                        namedQuery.getParameterValue(name)
                );
            }
        }

        return ((Number) nativeQuery.getSingleResult()).longValue();
    }

    private <ID extends Serializable, T extends AbstractEntity<ID>> Long count(Class<T> clazz) {
        CriteriaQuery<Long> c = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        c.select(getEntityManager().getCriteriaBuilder().count(c.from(clazz)));
        return getEntityManager().createQuery(c).getSingleResult();
    }
}
