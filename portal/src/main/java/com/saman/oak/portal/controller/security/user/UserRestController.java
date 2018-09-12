package com.saman.oak.portal.controller.security.user;

import com.saman.oak.core.web.RestActionController;
import com.saman.oak.portal.business.service.user.UserDetailsServiceImpl;
import com.saman.oak.portal.business.service.user.UserResource;
import com.saman.oak.portal.business.service.user.UserResourceAssembler;
import com.saman.oak.portal.controller.security.SecurityController;
import com.saman.oak.portal.domain.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@ExposesResourceFor(UserResource.class)
@Controller
@RequestMapping(value = UserRestController.USER_URL, produces = {"application/xml", "application/json"})
public class UserRestController extends RestActionController<UserEntity, UserResource> implements SecurityController {

    public static final String NAME = "userController";
    public static final String BODY = "/user";
    public static final String PAGE = "/user";

    public static final String USER_URL = NAME_SPACE + BODY + PAGE;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @Override
    @RequestMapping(value = CREATE)
    public HttpEntity<UserResource> create(UserEntity model) {
        Optional<UserResource> result = userService.save(model);
        return new HttpEntity<>(result.get());
    }

    @Override
    @RequestMapping(value = READ)
    public HttpEntity<UserResource> read(UserEntity model) {
//        Optional<UserModel> result = userService.findUnique(model);
//        return pagedAssembler.toResource(result.intValue(), userResourceAssembler);
        return null;
    }

    @Override
    @RequestMapping(value = UPDATE)
    public HttpEntity<UserResource> update(UserEntity model) {
//        Optional<UserModel> result = userService.update(model);
//        return pagedAssembler.toResource(result.intValue(), userResourceAssembler);
        return null;
    }

    @Override
    @RequestMapping(value = DELETE)
    public void delete(UserEntity model) {
    }

    @Override
    @RequestMapping(value = SEARCH)
    public List<PagedResources<UserResource>> search(UserEntity model) {
        return null;
    }
}
