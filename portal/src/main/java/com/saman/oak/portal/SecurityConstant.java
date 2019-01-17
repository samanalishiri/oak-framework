package com.saman.oak.portal;

import com.saman.oak.portal.constant.PathParam;
import com.saman.oak.portal.controller.security.LoginController;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public interface SecurityConstant {

    int SESSION_MAX = 1;

    String SCHEMA = "OAK";
    String COOKIE = "JSESSIONID";
    String CSRF_HEADER_NAME = "X-XSRF-TOKEN";
    String TOKEN_NAME = "XSRF-TOKEN";
    String FAILURE_URL = LoginController.LOGIN_VIEW + PathParam.DefaultValue.FAILED;
}
