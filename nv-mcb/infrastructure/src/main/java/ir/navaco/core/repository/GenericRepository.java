package ir.navaco.core.repository;

import ir.navaco.core.domain.AbstractEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Map;

/**
 * @author Esmaeil NikFekr on 4/29/2019
 */
public interface GenericRepository extends CrudRepository, SearchRepository {

    EntityManager getEntityManager();

    <ID extends Serializable, T extends AbstractEntity<ID>> long countAll(Class<T> clazz);

    <ID extends Serializable, T extends AbstractEntity<ID>> long countByNamedQuery(Class<T> clazz, String namedQuery);

    <ID extends Serializable, T extends AbstractEntity<ID>> long countByNamedQuery(Class<T> clazz, String namedQuery, Map<String, Object> params);
}
