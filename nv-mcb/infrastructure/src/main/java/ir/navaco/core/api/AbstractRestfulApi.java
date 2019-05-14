package ir.navaco.core.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ir.navaco.core.service.CrudService;
import ir.navaco.core.service.SearchService;
import ir.navaco.core.util.ExceptionUtils;
import ir.navaco.core.vo.AbstractVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * AbstractRestfulApi has a few common exposed service.
 * Exposed services include CRUD operation
 *
 * @author Saman Alishiri, samanalishiri@gmail.com
 */

public abstract class AbstractRestfulApi<I extends Serializable,
        V extends AbstractVo<I>,
        R extends CrudService<I, V> & SearchService<I, V>>
        implements CrudRestfulApi<I, V>, SearchRestfulApi<I, V> {

    private R service;

    public AbstractRestfulApi(R service) {
        this.service = service;
    }

    @ApiOperation(value = "${restfulApi.save.value}", notes = "${restfulApi.save.notes}")
    @PostMapping
    @Override
    public ResponseEntity<I> save(
            @ApiParam(name = "Value Object", value = "${restfulApi.save.param}", required = true)
            @Valid @RequestBody V v,
            @ApiParam(name = "errors", hidden = true) Errors errors) {

        if (errors.hasErrors()) {
            ExceptionUtils.transform(errors);
        }

        return ResponseEntity.ok(service.save(v).get());
    }

    @ApiOperation(value = "${restfulApi.findById.value}", notes = "${restfulApi.findById.notes}")
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<V> findById(
            @ApiParam(name = "ID", value = "${restfulApi.findById.param}", required = true, defaultValue = "0")
            @PathVariable("id") I id) {

        return ResponseEntity.ok(service.findById(id).get());
    }

    @ApiOperation(value = "${restfulApi.update.value}", notes = "${restfulApi.update.notes}")
    @PutMapping
    @Override
    public void update(
            @ApiParam(name = "Value Object", value = "${restfulApi.update.param}", required = true)
            @Valid @RequestBody V v, Errors errors) {

        if (errors.hasErrors()) {
            ExceptionUtils.transform(errors);
        }

        service.update(v);
    }

    @ApiOperation(value = "${restfulApi.delete.value}", notes = "${restfulApi.delete.notes}")
    @DeleteMapping("/{id}")
    @Override
    public void delete(
            @ApiParam(name = "ID", value = "${restfulApi.delete.param}", required = true, defaultValue = "0")
            @PathVariable("id") I id) {

        service.delete(id);
    }

    @GetMapping("find/all")
    @Override
    public ResponseEntity<List<V>> findAll() {
        return ResponseEntity.ok(service.findAll().get());
    }
}
