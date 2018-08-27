package com.saman.oak.portal.config.security.bean;

import com.saman.oak.core.converter.NumberConverter;
import com.saman.oak.core.exception.BaseException;
import com.saman.oak.portal.business.event.ForbiddenEvent;
import com.saman.oak.portal.domain.authority.AuthorityEntity;
import com.saman.oak.portal.domain.user.UserEntity;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Component("permissionEvaluator")
public class PermissionEvaluatorImpl implements PermissionEvaluator, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public PermissionEvaluatorImpl() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object permission) {

        Collection<? extends GrantedAuthority> authorities = ((UserEntity) authentication.getPrincipal()).getAuthorities();
        boolean hasPermission = authorities.stream().filter(item -> ((AuthorityEntity) item).hasPermission(NumberConverter.convertToLong(permission))).count() > 0;

        if (!hasPermission) {
            applicationContext.publishEvent(
                    new ForbiddenEvent<BaseException>(
                            new BaseException().code("error.accessDenied")
                            , new ArrayList<ConfigAttribute>()
                            , authentication
                    )
            );
        }

        return hasPermission;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }

}
