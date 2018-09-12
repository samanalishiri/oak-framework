package com.saman.oak.portal.config.security.bean.exception;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public class AuthenticationCredentialsNotFoundExceptionWrapper extends AuthenticationFailureExceptionWrapper<AuthenticationCredentialsNotFoundException> {

    private AuthenticationCredentialsNotFoundExceptionWrapper(AuthenticationException exception, Authentication authentication) {
        super(exception, authentication);
    }

    public AuthenticationCredentialsNotFoundExceptionWrapper() {

    }

    @Override
    public AuthenticationFailureExceptionWrapper newInstance(AuthenticationCredentialsNotFoundException exception, Authentication authentication) {
        return new AuthenticationCredentialsNotFoundExceptionWrapper(exception, authentication);
    }

    @Override
    public AuthenticationFailureExceptionWrapper emptyInstance() {
        return new AuthenticationCredentialsNotFoundExceptionWrapper();
    }
}
