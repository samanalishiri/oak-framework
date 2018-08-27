package com.saman.oak.core.builder;

public interface AbstractBuilder<T> {

    void init();

    T build();
}
