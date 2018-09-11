package com.saman.oak.portal.config.security.bean;

import com.saman.oak.portal.constant.Variable;
import com.saman.oak.portal.controller.security.LoginController;
import com.saman.oak.portal.domain.user.UserEntity;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationFailureExpiredEvent;
import org.springframework.security.authentication.event.AuthenticationFailureServiceExceptionEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;
    private String defaultFailureUrl = LoginController.LOGIN_VIEW + Variable.Default.FAILED;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
        String username = request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
        String password = request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY);

        Authentication auth = new UsernamePasswordAuthenticationToken(new UserEntity().setUsername(username).setPassword(password), null);

        AbstractAuthenticationFailureEvent event;

        if (exception instanceof BadCredentialsException) {
            event = new AuthenticationFailureBadCredentialsEvent(auth, exception);
        } else if (exception instanceof AccountExpiredException) {
            event = new AuthenticationFailureExpiredEvent(auth, exception);
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            event = new AuthenticationFailureBadCredentialsEvent(auth, exception);
        } else if (exception instanceof AuthenticationServiceException) {
            event = new AuthenticationFailureServiceExceptionEvent(auth, exception);
        } else {
            event = new AbstractAuthenticationFailureEvent(auth, exception) {
            };
        }

        applicationEventPublisher.publishEvent(event);

    }
}
