package com.saman.oak.portal.config.security.bean.exception;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public class AuthenticationServiceExceptionWrapper extends AuthenticationFailureExceptionWrapper<AuthenticationServiceException> {

    private AuthenticationServiceExceptionWrapper(AuthenticationException exception, Authentication authentication) {
        super(exception, authentication);
    }

    public AuthenticationServiceExceptionWrapper() {
        ;
    }

    @Override
    public AuthenticationServiceExceptionWrapper newInstance(AuthenticationServiceException exception, Authentication authentication) {
        return new AuthenticationServiceExceptionWrapper(exception, authentication);
    }

    @Override
    public AuthenticationFailureExceptionWrapper emptyInstance() {
        return new AuthenticationServiceExceptionWrapper();
    }
}
