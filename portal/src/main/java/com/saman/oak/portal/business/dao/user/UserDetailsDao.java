package com.saman.oak.portal.business.dao.user;

import com.saman.oak.core.business.dao.SpringDataJpaDao;
import com.saman.oak.portal.domain.user.UserEntity;

import java.util.Optional;

public interface UserDetailsDao extends SpringDataJpaDao<Long, UserEntity> {
    Optional<UserEntity> findByUsername(String username);
}
