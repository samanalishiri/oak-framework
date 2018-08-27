package com.saman.oak.portal.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends DefaultWebSecurityExpressionHandler {

    @Autowired
    private PermissionEvaluator permissionEvaluator;


    @Override
    public PermissionEvaluator getPermissionEvaluator() {
        return permissionEvaluator;
    }
}
