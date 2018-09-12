package com.saman.oak.portal.config.security.bean.exception;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public class AccountExpiredExceptionWrapper extends AuthenticationFailureExceptionWrapper<AccountExpiredException> {

    private AccountExpiredExceptionWrapper(AuthenticationException exception, Authentication authentication) {
        super(exception, authentication);
    }

    public AccountExpiredExceptionWrapper() {

    }

    @Override
    public AuthenticationFailureExceptionWrapper newInstance(AccountExpiredException exception, Authentication authentication) {
        return new AccountExpiredExceptionWrapper(exception, authentication);
    }

    @Override
    public AuthenticationFailureExceptionWrapper emptyInstance() {
        return new AccountExpiredExceptionWrapper();
    }
}
