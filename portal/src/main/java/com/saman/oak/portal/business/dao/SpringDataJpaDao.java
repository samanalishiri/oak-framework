package com.saman.oak.portal.business.dao;

import com.saman.oak.core.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface SpringDataJpaDao<E extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<E, ID>, CrudRepository<E, ID> {
}
