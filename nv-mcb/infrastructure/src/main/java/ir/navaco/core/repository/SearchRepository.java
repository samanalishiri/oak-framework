package ir.navaco.core.repository;

import ir.navaco.core.domain.AbstractEntity;
import ir.navaco.core.domain.SearchRequest;
import ir.navaco.core.domain.SearchResponse;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

/**
 * @author Esmaeil NikFekr on 4/29/2019
 */
public interface SearchRepository {

    <ID extends Serializable, T extends AbstractEntity<ID>> List<T>
    findAll(Class<T> clazz, SearchRequest request);

    <ID extends Serializable, T extends AbstractEntity<ID>> List<T>
    findAll(Class<T> clazz, SearchRequest request, Boolean isCache);

    <ID extends Serializable, T extends AbstractEntity<ID>> List<T> findByNamedQuery(Class<T> clazz, String namedQuery, SearchRequest request);

    <ID extends Serializable, T extends AbstractEntity<ID>> List<T> findByNamedQuery(Class<T> clazz, String namedQuery, Map<String, Object> params, SearchRequest request);

    <T> List<T> findByNamedQueryTemporalType(Class<T> clazz, String namedQuery, Map<String, AbstractMap.SimpleImmutableEntry<Object, Object>> params, SearchRequest request);

    <ID extends Serializable, T extends AbstractEntity<ID>> SearchResponse<T>
    searchAll(Class<T> clazz, SearchRequest request);

    <ID extends Serializable, T extends AbstractEntity<ID>> SearchResponse<T>
    searchByNamedQuery(Class<T> clazz, String namedQuery, SearchRequest request);

    <ID extends Serializable, T extends AbstractEntity<ID>> SearchResponse<T>
    searchByNamedQuery(Class<T> clazz, String namedQuery, Map<String, Object> params, SearchRequest request);

    <T> List<T> findByNamedQueryBatch(Class<T> clazz, String namedQuery,
                                      Map<String, Object> params, SearchRequest request);
}