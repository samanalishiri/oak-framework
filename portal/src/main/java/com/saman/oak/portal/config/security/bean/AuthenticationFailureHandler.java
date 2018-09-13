package com.saman.oak.portal.config.security.bean;

import com.saman.oak.portal.SecurityConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.saman.oak.core.utils.ServletUtils.forward;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler implements SecurityConstant, ApplicationEventPublisherAware {

    private final Logger logWriter = LoggerFactory.getLogger(AuthenticationFailureHandler.class);

    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private AuthenticationFailureMapper mapper;

    public AuthenticationFailureHandler() {
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {

        try {
            forward(request, response, FAILURE_URL);
            applicationEventPublisher.publishEvent(mapper.get(exception, request));

        } catch (Exception e) {
            logWriter.info("Authenticate failed");
        }
    }
}
