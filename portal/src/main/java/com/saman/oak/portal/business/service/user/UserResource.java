package com.saman.oak.portal.business.service.user;

import com.saman.oak.portal.domain.user.UserConstant;
import com.saman.oak.portal.domain.user.UserEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * Created by saman on 11/28/2017.
 */
@XStreamAlias("resource")
public class UserResource extends Resource<UserEntity> implements UserConstant {
    public static final String USER = "user";
    public static final String USERS = "users";

    public UserResource(UserEntity content, Link... links) {
        super(content, links);
    }
}
