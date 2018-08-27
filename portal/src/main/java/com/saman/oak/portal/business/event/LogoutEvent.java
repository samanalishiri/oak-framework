package com.saman.oak.portal.business.event;

import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.core.Authentication;

public class LogoutEvent extends AbstractAuthenticationEvent {
    public LogoutEvent(Authentication authentication) {
        super(authentication);
    }
}
