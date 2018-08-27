package com.saman.oak.portal.business.dao.user;

import com.saman.oak.portal.business.dao.SpringDataJpaDao;
import com.saman.oak.portal.domain.user.UserEntity;

public interface UserDetailsDao extends SpringDataJpaDao<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
