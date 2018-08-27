package com.saman.oak.core.web;

import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;

import java.util.List;

public interface RestAction<E, R> {

    HttpEntity<R> create(E model);

    HttpEntity<R> read(E model);

    HttpEntity<R> update(E model);

    void delete(E model);

    List<PagedResources<R>> search(E model);

}
