package com.saman.oak.core.business.service;

import com.saman.oak.core.business.Loggable;
import com.saman.oak.core.business.dao.CompleteDao;
import com.saman.oak.core.converter.Converter;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;

import java.io.Serializable;

/**
 * Created by Administrator on 9/29/2017.
 */
public interface Service<ID extends Serializable, M extends BaseModel<ID>, E extends BaseEntity<ID>, D extends CompleteDao<ID, E>> extends Loggable {

    void setEntityType(Class<? extends BaseEntity> c);

    void setModelType(Class<? extends BaseModel> c);

    void setDao(D d);

    D getDao();

    void setConverter(Converter<E, M> converter);

    Converter<E, M> getConverter();

}
