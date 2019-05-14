package ir.navaco.core.api;

import ir.navaco.core.vo.AbstractVo;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Force different search operations
 *
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public interface SearchRestfulApi<I extends Serializable, V extends AbstractVo<I>> {

    /**
     * find all records in a batch
     *
     * @return
     */
    ResponseEntity<List<V>> findAll();

}
