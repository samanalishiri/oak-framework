package com.saman.oak.portal.config.security.bean;

import com.saman.oak.portal.SecurityConstant;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by saman on 10/22/2017.
 */
public class CsrfHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        if (Objects.nonNull(csrf)) {

            Cookie cookie = WebUtils.getCookie(request, SecurityConstant.XSRF_TOKEN);
            String token = csrf.getToken();

            if (Objects.isNull(cookie) || (Objects.nonNull(token) && !token.equals(cookie.getValue()))) {
                cookie = new Cookie(SecurityConstant.XSRF_TOKEN, token);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

        filterChain.doFilter(request, response);
    }
}