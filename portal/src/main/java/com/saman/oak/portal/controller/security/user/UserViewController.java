package com.saman.oak.portal.controller.security.user;

import com.saman.oak.core.web.ViewController;
import com.saman.oak.portal.business.service.user.UserResource;
import com.saman.oak.portal.controller.security.SecurityController;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.saman.oak.portal.controller.HomeController.HOME_URL;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@ExposesResourceFor(UserResource.class)
@Controller
@RequestMapping(value = UserViewController.USER_URL)
public class UserViewController extends ViewController implements SecurityController {

    public static final String NAME = "userController";
    public static final String BODY = "/user";
    public static final String PAGE = "/user";

    public static final String USER_URL = NAME_SPACE + BODY + PAGE;

    @Override
    @RequestMapping(value = VIEW)
    public String view() {
        homeContent.setTitle("jsp.user.title");
        homeContent.setUrl(USER_URL + JSP);
        return HOME_URL;
    }
}
