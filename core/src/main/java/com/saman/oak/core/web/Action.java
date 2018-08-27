package com.saman.oak.core.web;

import org.springframework.http.HttpEntity;

import java.util.List;

public interface Action<E, R> {

    HttpEntity<R> create(E model);

    HttpEntity<R> read(E model);

    HttpEntity<R> update(E model);

    void delete(E model);

    List<R> search(E model);

}
