package com.saman.oak.core.utils;

import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

import java.util.Collection;

/**
 * Created by saman on 2/5/2018.
 */
public class SpringDataUtil {

    public <T> PagedResources<Resource<T>> createPagedResources(Collection<Resource<T>> resources) {
        long size = 1;
        long number = 1;
        long totalElements = 1;
        long totalPages = totalElements / size;
        PagedResources.PageMetadata pageMetadata = new PagedResources.PageMetadata(size, number, totalElements, totalPages);
        PagedResources<Resource<T>> pagedResources = new PagedResources<>(resources, pageMetadata);
        return pagedResources;
    }
}
