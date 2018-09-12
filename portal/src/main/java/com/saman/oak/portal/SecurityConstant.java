package com.saman.oak.portal;

import com.saman.oak.portal.constant.PathVariable;
import com.saman.oak.portal.controller.security.LoginController;

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
    String FAILURE_URL = LoginController.LOGIN_VIEW + PathVariable.DefaultValue.FAILED;
}
