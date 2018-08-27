package com.saman.oak.hibernate.domain;

import com.saman.oak.core.business.Loggable;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.domain.Response;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class HibernateResponse<T extends BaseEntity> extends Response<Session, T> implements Loggable {

    private Iterator<T> iterator;

    public HibernateResponse() {
    }

    public HibernateResponse(Session session, T... entities) {
        super(session, entities);
    }

    public static HibernateResponse create(Session session, BaseEntity... entities) {
        return new HibernateResponse(session, entities);
    }

    @Override
    public void add(T... entity) {
        entities.addAll(Arrays.asList(entity));
    }

    @Override
    public List<T> list() {
        return entities;
    }

    @Override
    public T unique() {
        iterator();
        return (iterator.hasNext()) ? iterator.next() : (T) HibernateEmptyEntityProxy.Factory.create();
    }

    @Override
    public Session getContext() {
        return super.getContext();
    }

    @Override
    public void setContext(Session session) {
        super.setContext(session);
    }

    @Override
    public void close() {
        if (Objects.nonNull(getContext()) && (getContext().isOpen() || getContext().isConnected())) {
            getContext().close();
        }
    }

    private void iterator() {
        iterator = entities.iterator();
    }
}
