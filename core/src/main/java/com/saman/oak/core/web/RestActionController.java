package com.saman.oak.core.web;

import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import java.io.Serializable;

public abstract class RestActionController<ID extends Serializable, M extends BaseModel, E extends BaseEntity, R extends Resource<E>> extends WebContentInterceptor implements Controller, RestAction<ID, M, E, R> {


    public static final String LIST = "/list" + ACTION;
    public static final String SAVE = "/save" + ACTION;
    public static final String EDIT = "/edit" + ACTION;
    public static final String DELETE = "/delete" + ACTION;
    public static final String FIND = "/find" + ACTION;
    public static final String SEARCH = "/search" + ACTION;
    public static final String REPORT = "/report" + ACTION;

    @Autowired
    protected PagedResourcesAssembler<R> pagedAssembler;


}
