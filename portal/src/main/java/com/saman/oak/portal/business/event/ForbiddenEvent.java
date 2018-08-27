package com.saman.oak.portal.business.event;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.event.AuthorizedEvent;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public class ForbiddenEvent<T> extends AuthorizedEvent {

    public ForbiddenEvent(T secureObject, Collection<ConfigAttribute> attributes, Authentication authentication) {
        super(secureObject, attributes, authentication);
    }

    @Override
    public T getSource() {
        return (T) super.getSource();
    }
}
