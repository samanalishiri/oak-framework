package com.saman.oak.portal.controller.exception;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationFailureExpiredEvent;
import org.springframework.security.authentication.event.AuthenticationFailureServiceExceptionEvent;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionProvider implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @ExceptionHandler({BadCredentialsException.class})
    public void sendBadCredentialsEvent(BadCredentialsException e) {
        applicationEventPublisher.publishEvent(new AuthenticationFailureBadCredentialsEvent(null, e));
    }

    @ExceptionHandler({AccountExpiredException.class})
    public void sendBadCredentialsEvent(AccountExpiredException e) {
        applicationEventPublisher.publishEvent(new AuthenticationFailureExpiredEvent(null, e));
    }

    @ExceptionHandler({AuthenticationCredentialsNotFoundException.class})
    public void sendBadCredentialsEvent(AuthenticationCredentialsNotFoundException e) {
        applicationEventPublisher.publishEvent(new AuthenticationFailureBadCredentialsEvent(null, e));
    }

    @ExceptionHandler({AuthenticationServiceException.class})
    public void sendBadCredentialsEvent(AuthenticationServiceException e) {
        applicationEventPublisher.publishEvent(new AuthenticationFailureServiceExceptionEvent(null, e));
    }
}
