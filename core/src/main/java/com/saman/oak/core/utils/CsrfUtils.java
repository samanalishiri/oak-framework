package com.saman.oak.core.utils;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public final class CsrfUtils {

    private CsrfUtils() {
    }

    public static boolean hasCookie(HttpServletRequest request, String name) {
        return Objects.nonNull(WebUtils.getCookie(request, name));
    }

    public static String getCookie(HttpServletRequest request, String name) {
        return hasCookie(request, name) ? WebUtils.getCookie(request, name).getValue() : "";
    }

    public static String getToken(HttpServletRequest request) {
        return hasToken(request) ? ((CsrfToken) request.getAttribute(CsrfToken.class.getName())).getToken() : "";
    }

    public static boolean hasToken(HttpServletRequest request) {
        return Objects.nonNull(request.getAttribute(CsrfToken.class.getName()));
    }

    public static void addCookieToResponse(HttpServletResponse response, String token, String name) {
        Cookie cookie = new Cookie(name, token);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
