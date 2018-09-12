package com.saman.oak.core.utils;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public final class SpringSecurityObjectFactory {

    private SpringSecurityObjectFactory() {
    }

    public static UserDetails createPrincipal(HttpServletRequest request) {

        String username = getUsername(request);
        String password = getPassword(request);

        return User.builder().username(username).password(password).build();
    }

    public static String getUsername(HttpServletRequest request) {
        return request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
    }

    public static String getPassword(HttpServletRequest request) {
        return request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY);
    }
}
