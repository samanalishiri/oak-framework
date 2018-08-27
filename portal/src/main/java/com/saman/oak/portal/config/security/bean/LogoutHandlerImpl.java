package com.saman.oak.portal.config.security.bean;

import com.saman.oak.portal.business.event.LogoutEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class LogoutHandlerImpl implements LogoutHandler, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        String clientIpAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (Objects.isNull(clientIpAddress)) {
            clientIpAddress = httpServletRequest.getRemoteAddr();
        }

        if (Objects.nonNull(authentication)) {
            applicationContext.publishEvent(new LogoutEvent(authentication));
        }
    }
}
