package com.saman.oak.portal.config.security.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.saman.oak.core.utils.ServletUtils.forward;
import static com.saman.oak.portal.SecurityConstant.FAILURE_URL;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private EventPublisher eventPublisher;

    @Autowired
    private AuthenticationFailureMapper mapper;

    public AuthenticationFailureHandler() {
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws ServletException, IOException {
        forward(request, response, FAILURE_URL);
        eventPublisher.publish(mapper.get(exception, request));
    }
}
