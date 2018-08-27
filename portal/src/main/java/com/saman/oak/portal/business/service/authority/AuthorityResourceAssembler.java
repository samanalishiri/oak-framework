package com.saman.oak.portal.business.service.authority;

import com.saman.oak.portal.controller.security.authority.AuthorityRestController;
import com.saman.oak.portal.domain.authority.AuthorityEntity;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by saman on 11/28/2017.
 */
@Component
public class AuthorityResourceAssembler extends ResourceAssemblerSupport<AuthorityEntity, AuthorityResource> {

//    @Autowired
//    private EntityLinks entityLinks;

    public AuthorityResourceAssembler() {
        super(AuthorityRestController.class, AuthorityResource.class);
    }

    @Override
    public AuthorityResource toResource(AuthorityEntity user) {
        AuthorityResource resource = createResourceWithId(user.getId(), user);
        return resource;
    }

    @Override
    protected AuthorityResource instantiateResource(AuthorityEntity entity) {
        return new AuthorityResource(entity);
    }
}
