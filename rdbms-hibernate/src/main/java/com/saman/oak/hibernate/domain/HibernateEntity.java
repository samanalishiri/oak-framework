package com.saman.oak.hibernate.domain;


import com.saman.oak.core.domain.BaseEntity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class HibernateEntity extends BaseEntity<Long> {
}
