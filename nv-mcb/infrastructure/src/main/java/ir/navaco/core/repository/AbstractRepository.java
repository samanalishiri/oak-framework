package ir.navaco.core.repository;

import ir.navaco.core.domain.AbstractEntity;
import ir.navaco.core.domain.SearchRequest;
import ir.navaco.core.domain.SearchResponse;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Esmaeil NikFekr on 5/11/2019
 */
public interface AbstractRepository<ID extends Serializable, T extends AbstractEntity<ID>> {
    long countAll();

    long countByNamedQuery(String namedQuery);

    long countByNamedQuery(String namedQuery, Map<String, Object> params);

    List<T> findAll(SearchRequest request);

    List<T> findByNamedQuery(String namedQuery, SearchRequest request);

    List<T> findByNamedQuery(String namedQuery, Map<String, Object> params, SearchRequest request);

    SearchResponse<T> searchAll(SearchRequest request);

    SearchResponse<T> searchByNamedQuery(String namedQuery, SearchRequest request);

    SearchResponse<T> searchByNamedQuery(String namedQuery, Map<String, Object> params, SearchRequest request);

    Optional<T> getReference(ID id);

    Optional<T> findOne(ID id);

    Optional<T> findOne(String query, Map<String, Object> params);

    Optional<T> persist(T entity);

    Optional<T> merge(T entity);

    Optional<T> delete(T entity);

    Optional<T> delete(ID entityId);
}
