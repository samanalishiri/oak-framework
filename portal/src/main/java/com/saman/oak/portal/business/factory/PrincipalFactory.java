package com.saman.oak.portal.business.factory;

import com.saman.oak.portal.domain.user.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public final class PrincipalFactory {

    private PrincipalFactory() {
    }

    public static UserDetails createPrincipal(HttpServletRequest request) {

        String username = getUsername(request);
        String password = getPassword(request);

        return new UserEntity().setUsername(username).setPassword(password);
    }

    public static String getUsername(HttpServletRequest request) {
        return request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
    }

    public static String getPassword(HttpServletRequest request) {
        return request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY);
    }
}
