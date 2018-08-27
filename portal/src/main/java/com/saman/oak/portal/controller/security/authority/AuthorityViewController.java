package com.saman.oak.portal.controller.security.authority;

import com.saman.oak.core.web.ViewController;
import com.saman.oak.portal.business.service.authority.AuthorityResource;
import com.saman.oak.portal.controller.security.SecurityController;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.saman.oak.portal.controller.HomeController.HOME_URL;


@ExposesResourceFor(AuthorityResource.class)
@Controller
@RequestMapping(value = AuthorityViewController.AUTHORITY_URL, produces = {"application/xml", "application/json"})
public class AuthorityViewController extends ViewController implements SecurityController {

    public static final String NAME = "authorityController";
    public static final String BODY = "/authority";
    public static final String PAGE = "/authority";

    public static final String AUTHORITY_URL = NAME_SPACE + BODY + PAGE;

    @Override
    @RequestMapping(value = VIEW)
    public String view() {
        homeContent.setTitle("jsp.user.title");
        homeContent.setUrl(AUTHORITY_URL + JSP);
        return HOME_URL;
    }
}
