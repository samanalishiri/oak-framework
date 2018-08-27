package com.saman.oak.core.web;

import com.saman.oak.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

public abstract class RestActionController<E extends BaseEntity, R extends Resource<E>> extends WebContentInterceptor implements Controller, RestAction<E, R> {


    public static final String LIST = "/list" + ACTION;
    public static final String CREATE = "/create" + ACTION;
    public static final String UPDATE = "/update" + ACTION;
    public static final String DELETE = "/delete" + ACTION;
    public static final String READ = "/read" + ACTION;
    public static final String SEARCH = "/search" + ACTION;
    public static final String REPORT = "/report" + ACTION;

    @Autowired
    protected PagedResourcesAssembler<R> pagedAssembler;


}
