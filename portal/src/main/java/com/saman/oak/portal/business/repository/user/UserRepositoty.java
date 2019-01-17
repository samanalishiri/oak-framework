package com.saman.oak.portal.business.repository.user;

import com.saman.oak.core.business.dao.SpringDataJpaRepository;
import com.saman.oak.portal.domain.user.UserEntity;

import java.util.Optional;

public interface UserRepositoty extends SpringDataJpaRepository<Long, UserEntity> {
    Optional<UserEntity> findByUsername(String username);
}
