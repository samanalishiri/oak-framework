package com.saman.oak.portal.config.security.bean.exception;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public abstract class AuthenticationFailureExceptionWrapper<T extends AuthenticationException> {

    protected AuthenticationException exception;
    protected Authentication authentication;

    public AuthenticationFailureExceptionWrapper(AuthenticationException exception, Authentication authentication) {
        this.exception = exception;
        this.authentication = authentication;
    }

    public AuthenticationFailureExceptionWrapper() {
    }

    public AuthenticationException getException() {
        return exception;
    }

    public void setException(AuthenticationException exception) {
        this.exception = exception;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public abstract AuthenticationFailureExceptionWrapper newInstance(T exception, Authentication authentication);

    public abstract AuthenticationFailureExceptionWrapper emptyInstance();
}
