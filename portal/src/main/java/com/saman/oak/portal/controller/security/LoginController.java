package com.saman.oak.portal.controller.security;

import com.saman.oak.core.utils.StringUtils;
import com.saman.oak.core.web.ViewController;
import com.saman.oak.portal.constant.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Saman Alishiri, samanalishiri@gmail.com
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

    @RequestMapping(value = LOGIN_VIEW + "/{" + PathParam.FAILED + "}")
    public String login(@PathVariable(PathParam.FAILED) boolean failed) {
        return LOGIN_URL;
    }
}
