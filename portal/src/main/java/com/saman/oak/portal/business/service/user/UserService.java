package com.saman.oak.portal.business.service.user;

import com.saman.oak.core.business.dao.SpringDataJpaDao;
import com.saman.oak.core.business.hateoas.service.ResourceableCompleteService;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;
import org.springframework.hateoas.Resource;

import java.io.Serializable;

public interface UserService<ID extends Serializable, M extends BaseModel<ID>, R extends Resource<E>, E extends BaseEntity<ID>, D extends SpringDataJpaDao<ID, E>> extends ResourceableCompleteService<ID, M, E, R, D> {
}
