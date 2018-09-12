package com.saman.oak.portal;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 *
 */

public interface SecurityConstant {

    int SESSION_MAX = 1;

    String SCHEMA = "SECURITY";
    String COOKIE = "JSESSIONID";
    String CSRF_HEADER_NAME = "X-XSRF-TOKEN";
    String TOKEN_NAME = "XSRF-TOKEN";
}
