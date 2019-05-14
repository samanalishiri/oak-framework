package ir.navaco.core.api;

import ir.navaco.core.vo.AbstractVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import java.io.Serializable;

/**
 * Forced CRUD operation
 *
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public interface CrudRestfulApi<I extends Serializable, V extends AbstractVo<I>> {

    /**
     * Save a new object
     *
     * @param v
     * @param errors
     * @return
     */
    ResponseEntity<I> save(V v, Errors errors);

    /**
     * Find by identifier
     *
     * @param id
     * @return
     */
    ResponseEntity<V> findById(I id);

    /**
     * Update an persisted opbject
     *
     * @param v
     * @param errors
     */
    void update(V v, Errors errors);

    /**
     * Delete persisted object by identifier
     *
     * @param id
     */
    void delete(I id);

}
