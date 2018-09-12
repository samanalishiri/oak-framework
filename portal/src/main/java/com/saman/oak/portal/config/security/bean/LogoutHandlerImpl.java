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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.saman.oak.core.utils.StringUtils.notEmpty;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@Component
public class LogoutHandlerImpl implements LogoutHandler, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        if (Objects.isNull(authentication))
            return;

        publishLogoutEvent(authentication, createClientParam(httpServletRequest));
    }

    private void publishLogoutEvent(Authentication authentication, Map<LogoutEvent.Info, Object> param) {
        applicationContext.publishEvent(new LogoutEvent(authentication, param));
    }

    private Map<LogoutEvent.Info, Object> createClientParam(HttpServletRequest httpServletRequest) {
        Map<LogoutEvent.Info, Object> map = new HashMap<>();

        if (notEmpty(httpServletRequest.getHeader("X-FORWARDED-FOR")))
            map.put(LogoutEvent.Info.CLIENT_IP_ADDRESS, httpServletRequest.getRemoteAddr());

        return map;
    }
}
