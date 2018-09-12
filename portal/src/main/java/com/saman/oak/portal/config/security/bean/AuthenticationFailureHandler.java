package com.saman.oak.portal.config.security.bean;

import com.saman.oak.portal.SecurityConstant;
import com.saman.oak.portal.config.security.bean.exception.AccountExpiredExceptionWrapper;
import com.saman.oak.portal.config.security.bean.exception.AuthenticationCredentialsNotFoundExceptionWrapper;
import com.saman.oak.portal.config.security.bean.exception.AuthenticationFailureExceptionWrapper;
import com.saman.oak.portal.config.security.bean.exception.AuthenticationServiceExceptionWrapper;
import com.saman.oak.portal.config.security.bean.exception.BadCredentialsExceptionWrapper;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.saman.oak.core.utils.ServletUtils.forward;
import static com.saman.oak.core.utils.SpringSecurityObjectFactory.createPrincipal;
import static com.saman.oak.core.utils.SpringSecurityObjectFactory.getPassword;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler implements SecurityConstant {

    Map<Class<?>, AuthenticationFailureExceptionWrapper> map = new HashMap() {{
        put(AuthenticationServiceException.class, new AuthenticationServiceExceptionWrapper());
        put(AuthenticationCredentialsNotFoundException.class, new AuthenticationCredentialsNotFoundExceptionWrapper());
        put(AccountExpiredException.class, new AccountExpiredExceptionWrapper());
        put(BadCredentialsException.class, new BadCredentialsExceptionWrapper());
    }};


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException, AuthenticationException {

        forward(request, response, FAILURE_URL);

        AuthenticationFailureExceptionWrapper exceptionWrapper = map.get(exception.getClass());
        exceptionWrapper.newInstance(exception, new UsernamePasswordAuthenticationToken(createPrincipal(request), getPassword(request)));

        throw exception;
    }
}
