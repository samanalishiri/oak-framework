package com.saman.oak.core.business.dao;

import com.saman.oak.core.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface SpringDataJpaDao<ID extends Serializable, E extends BaseEntity<ID>> extends JpaRepository<E, ID>, CrudRepository<E, ID> {
}
