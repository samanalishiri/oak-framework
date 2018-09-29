package com.saman.oak.core.business.hateoas.service;

import com.saman.oak.core.business.Loggable;
import com.saman.oak.core.business.dao.SpringDataJpaDao;
import com.saman.oak.core.converter.Converter;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;
import org.springframework.hateoas.Resource;

import java.io.Serializable;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public interface ResourceableService<ID extends Serializable, M extends BaseModel<ID>, E extends BaseEntity<ID>, R extends Resource<E>, D extends SpringDataJpaDao<ID, E>> extends Loggable {

    void setEntityType(Class<? extends BaseEntity> c);

    void setModelType(Class<? extends BaseModel> c);

    D getDao();

    void setDao(D d);

    Converter<E, M> getConverter();

    void setConverter(Converter<E, M> converter);
}
