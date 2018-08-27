package com.saman.oak.core.business.dao;

import com.saman.oak.core.business.Loggable;
import com.saman.oak.core.domain.BaseEntity;

import java.io.Serializable;


public interface Dao<ID extends Serializable, E extends BaseEntity<ID>> extends Loggable {

    void setEntityType(Class<E> c);
}
