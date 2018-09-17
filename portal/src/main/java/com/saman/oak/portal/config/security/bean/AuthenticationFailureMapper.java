package com.saman.oak.portal.config.security.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationFailureExpiredEvent;
import org.springframework.security.authentication.event.AuthenticationFailureServiceExceptionEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static com.saman.oak.portal.business.factory.SpringSecurityObjectFactory.createPrincipal;
import static com.saman.oak.portal.business.factory.SpringSecurityObjectFactory.getPassword;

@Component
public class AuthenticationFailureMapper {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationFailureMapper.class);

    private final Map<Class<? extends AuthenticationException>, Class<? extends AbstractAuthenticationFailureEvent>> map = new HashMap<>();

    public AuthenticationFailureMapper() {
        initMap();
    }

    private void initMap() {
        map.put(AuthenticationServiceException.class, AuthenticationFailureServiceExceptionEvent.class);
        map.put(AuthenticationCredentialsNotFoundException.class, AuthenticationFailureBadCredentialsEvent.class);
        map.put(AccountExpiredException.class, AuthenticationFailureExpiredEvent.class);
        map.put(BadCredentialsException.class, AuthenticationFailureBadCredentialsEvent.class);
        map.put(InternalAuthenticationServiceException.class, AuthenticationFailureBadCredentialsEvent.class);
    }

    public AbstractAuthenticationFailureEvent get(AuthenticationException exception, HttpServletRequest request) {

        try {
            return map.get(exception.getClass())
                    .getConstructor(Authentication.class, AuthenticationException.class)
                    .newInstance(new UsernamePasswordAuthenticationToken(createPrincipal(request), getPassword(request)), exception);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e.getMessage());
        }

        return null;
    }
}
