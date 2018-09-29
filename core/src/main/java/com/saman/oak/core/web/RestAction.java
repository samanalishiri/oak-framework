package com.saman.oak.core.web;

import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;

import java.util.List;

public interface RestAction<ID, M, E, R> {

    HttpEntity<R> save(M model);

    HttpEntity<R> find(ID id);

    HttpEntity<R> edit(M model);

    void delete(M model);

    List<PagedResources<R>> search(M model);

}
