package com.saman.oak.core.business.hateoas.service;

import com.saman.oak.core.business.Loggable;
import com.saman.oak.core.business.dao.SpringDataJpaRepository;
import com.saman.oak.core.converter.Converter;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.io.Serializable;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public interface ResourceableService<ID extends Serializable,
        M extends BaseModel<ID>,
        E extends BaseEntity<ID>,
        R extends Resource<E>,
        D extends SpringDataJpaRepository<ID, E>> extends Loggable {

    D getRepository();

    void setRepository(D repository);

    Converter<E, M> getConverter();

    void setConverter(Converter<E, M> converter);

    ResourceAssemblerSupport<E, R> getAssembler();

    void setAssembler(ResourceAssemblerSupport<E, R> assembler);
}
