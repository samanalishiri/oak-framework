package com.saman.oak.portal.controller;

import com.saman.oak.core.utils.ControllerUtil;
import com.saman.oak.core.utils.StringUtils;
import com.saman.oak.core.web.ViewController;
import com.saman.oak.portal.controller.security.LoginController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */

@Controller(HomeController.NAME)
public class HomeController extends ViewController {
    public static final String NAME = "homeController";
    public static final String BODY = StringUtils.EMPTY;
    public static final String PAGE = "/home";

    public static final String HOME_URL = BODY + PAGE;

    @RequestMapping(value = {ROOT})
    public String root() {
        return ControllerUtil.forward(LoginController.LOGIN_VIEW);
    }

    @RequestMapping(value = {HOME_URL + VIEW})
    public String view() {
        homeContent.setTitle("jsp.home.title");
        homeContent.setUrl("blank.jsp");
        return HOME_URL;
    }
}
