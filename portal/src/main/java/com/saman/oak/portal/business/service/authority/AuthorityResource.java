package com.saman.oak.portal.business.service.authority;

import com.saman.oak.portal.domain.authority.AuthorityEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * Created by saman on 11/28/2017.
 */
@XStreamAlias("resource")
public class AuthorityResource extends Resource<AuthorityEntity> {
    public static final String USER = "authority";
    public static final String USERS = "authorities";

    public AuthorityResource(AuthorityEntity content, Link... links) {
        super(content, links);
    }
}
