package com.saman.oak.core.domain;

import com.saman.oak.core.utils.CollectionUtils;

import java.util.Arrays;
import java.util.List;

public abstract class Response<C, E extends BaseEntity> implements AutoCloseable {

    protected List<E> entities = CollectionUtils.EMPTY_LIST;

    protected C context;

    public Response() {
    }

    public Response(C c, E... entities) {
        this.entities.addAll(Arrays.asList(entities));
        this.context = c;
    }

    public abstract E unique();

    public abstract List<E> list();

    public abstract void add(E... e);

    public C getContext() {
        return context;
    }

    public void setContext(C c) {
        this.context = c;
    }

    @Override
    public abstract void close();
}
