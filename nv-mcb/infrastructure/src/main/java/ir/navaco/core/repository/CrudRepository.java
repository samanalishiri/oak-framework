package ir.navaco.core.repository;

import ir.navaco.core.domain.AbstractEntity;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * @author Esmaeil NikFekr on 4/29/2019
 */
public interface CrudRepository {

    <ID extends Serializable, T extends AbstractEntity<ID>> Optional<T> getReference(Class<T> clazz, ID id);

    <ID extends Serializable, T extends AbstractEntity<ID>> Optional<T> findOne(Class<T> clazz, ID id);

    <ID extends Serializable, T extends AbstractEntity<ID>> Optional<T> findOne(Class<T> clazz, String query, Map<String, Object> params);

    <ID extends Serializable, T extends AbstractEntity<ID>> Optional<T> persist(Class<T> clazz, T entity);

    <ID extends Serializable, T extends AbstractEntity<ID>> Optional<T> merge(Class<T> clazz, T entity);

    <ID extends Serializable, T extends AbstractEntity<ID>> Optional<T> delete(Class<T> clazz, T entity);

    <ID extends Serializable, T extends AbstractEntity<ID>> Optional<T> delete(Class<T> clazz, ID entityId);
}
