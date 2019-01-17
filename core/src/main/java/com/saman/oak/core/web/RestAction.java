package com.saman.oak.core.web;

import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;

import java.util.List;

public interface RestAction<ID, M, E, R> {

    HttpEntity<ID> save(M model);

    HttpEntity<R> find(ID id);

    void edit(M model);

    void delete(M model);

    List<PagedResources<R>> search(M model);

}
