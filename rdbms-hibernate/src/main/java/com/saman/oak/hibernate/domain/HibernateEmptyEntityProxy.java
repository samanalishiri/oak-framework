package com.saman.oak.hibernate.domain;

import com.saman.oak.core.domain.EmptyEntity;

public class HibernateEmptyEntityProxy extends HibernateEntity {

    private EmptyEntity entity;

    public HibernateEmptyEntityProxy() {
        entity = new EmptyEntity();
    }

    @Override
    public Long getId() {
        return entity.getId();
    }

    public static class Factory {
        public static HibernateEmptyEntityProxy create() {
            return new HibernateEmptyEntityProxy();
        }
    }

}
