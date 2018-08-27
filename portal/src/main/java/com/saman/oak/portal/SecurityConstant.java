package com.saman.oak.portal;

public interface SecurityConstant {

    int SESSION_MAX = 1;

    String SCHEMA = "SECURITY";
    String COOKIE = "JSESSIONID";
    String CSRF_HEADER_NAME = "X-XSRF-TOKEN";
    String XSRF_TOKEN = "XSRF-TOKEN";
}
