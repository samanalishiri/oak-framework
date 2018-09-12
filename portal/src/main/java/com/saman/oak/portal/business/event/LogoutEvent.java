package com.saman.oak.portal.business.event;

import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public class LogoutEvent extends AbstractAuthenticationEvent {

    private Map<Info, Object> additionalInfo = new HashMap<>();

    public LogoutEvent(Authentication authentication) {
        super(authentication);
    }

    public LogoutEvent(Authentication authentication, Map<Info, Object> additionalInfo) {
        super(authentication);
        this.additionalInfo = additionalInfo;
    }

    public LogoutEvent addInfo(Info key, Object value) {
        additionalInfo.put(key, value);
        return this;
    }

    public enum Info {
        CLIENT_IP_ADDRESS,
        ;
    }
}
