package com.saman.oak.portal.controller.security.authority;

import com.saman.oak.core.web.RestActionController;
import com.saman.oak.portal.business.service.authority.AuthorityResource;
import com.saman.oak.portal.controller.security.SecurityController;
import com.saman.oak.portal.domain.authority.AuthorityEntity;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@ExposesResourceFor(AuthorityResource.class)
@Controller
@RequestMapping(value = AuthorityRestController.AUTHORITY_URL, produces = {"application/xml", "application/json"})
public class AuthorityRestController extends RestActionController<AuthorityEntity, AuthorityResource> implements SecurityController {

    public static final String NAME = "authorityController";
    public static final String BODY = "/authority";
    public static final String PAGE = "/authority";

    public static final String AUTHORITY_URL = NAME_SPACE + BODY + PAGE;

    @Override
    @RequestMapping(value = CREATE)
    public HttpEntity<AuthorityResource> create(AuthorityEntity model) {
        return null;
    }

    @Override
    @RequestMapping(value = READ)
    public HttpEntity<AuthorityResource> read(AuthorityEntity model) {
        return null;
    }

    @Override
    @RequestMapping(value = UPDATE)
    public HttpEntity<AuthorityResource> update(AuthorityEntity model) {
        return null;
    }

    @Override
    @RequestMapping(value = DELETE)
    public void delete(AuthorityEntity model) {

    }

    @Override
    @RequestMapping(value = SEARCH)
    public List<PagedResources<AuthorityResource>> search(AuthorityEntity model) {
        return null;
    }


}
