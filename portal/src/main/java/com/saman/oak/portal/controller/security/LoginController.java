package com.saman.oak.portal.controller.security;

import com.saman.oak.core.utils.StringUtils;
import com.saman.oak.core.web.ViewController;
import com.saman.oak.portal.constant.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@Controller(LoginController.NAME)
public class LoginController extends ViewController implements SecurityController {
    public static final String NAME = "loginController";
    public static final String BODY = StringUtils.EMPTY;
    public static final String PAGE = "/login";

    public static final String LOGIN_URL = NAME_SPACE + BODY + PAGE;
    public static final String LOGIN_VIEW = LOGIN_URL + VIEW;
    public static final String LOGIN_ACTION = LOGIN_URL + ACTION;
    public static final String LOGOUT_ACTION = NAME_SPACE + "/logout" + ACTION;

    @RequestMapping(value = LOGIN_VIEW)
    public String view() {
        return LOGIN_URL;
    }

    @RequestMapping(value = LOGIN_VIEW + "/{" + PathVariable.FAILED + "}")
    public String login(@org.springframework.web.bind.annotation.PathVariable(PathVariable.FAILED) boolean failed) {
        return LOGIN_URL;
    }
}
