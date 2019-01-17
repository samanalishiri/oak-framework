package com.saman.oak.portal.business.service.user;

import com.saman.oak.core.business.hateoas.service.ResourceableCompleteService;
import com.saman.oak.portal.business.repository.user.UserRepositoty;
import com.saman.oak.portal.domain.user.UserEntity;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public interface UserService extends ResourceableCompleteService<Long, UserModel, UserEntity, UserResource, UserRepositoty> {
}
