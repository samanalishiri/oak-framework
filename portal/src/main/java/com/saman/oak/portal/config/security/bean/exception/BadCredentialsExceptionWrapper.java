package com.saman.oak.portal.config.security.bean.exception;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public class BadCredentialsExceptionWrapper extends AuthenticationFailureExceptionWrapper<BadCredentialsException> {

    private BadCredentialsExceptionWrapper(AuthenticationException exception, Authentication authentication) {
        super(exception, authentication);
    }

    public BadCredentialsExceptionWrapper() {

    }

    @Override
    public AuthenticationFailureExceptionWrapper newInstance(BadCredentialsException exception, Authentication authentication) {
        return new BadCredentialsExceptionWrapper(exception, authentication);
    }

    @Override
    public AuthenticationFailureExceptionWrapper emptyInstance() {
        return new BadCredentialsExceptionWrapper();
    }
}
