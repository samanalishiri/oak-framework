package com.saman.oak.portal.config.security.bean;

import com.saman.oak.core.utils.StringUtils;
import com.saman.oak.portal.SecurityConstant;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.saman.oak.core.utils.CsrfUtils.addCookieToResponse;
import static com.saman.oak.core.utils.CsrfUtils.getCookie;
import static com.saman.oak.core.utils.CsrfUtils.getToken;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public class CsrfHeaderFilter extends OncePerRequestFilter implements SecurityConstant {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        addCookieIfNeeded(request, response);
        filterChain.doFilter(request, response);
    }

    private void addCookieIfNeeded(HttpServletRequest request, HttpServletResponse response) {
        String token = getToken(request);
        String cookie = getCookie(request, TOKEN_NAME);

        if (StringUtils.notEqual(token, cookie))
            addCookieToResponse(response, token, TOKEN_NAME);
    }

}