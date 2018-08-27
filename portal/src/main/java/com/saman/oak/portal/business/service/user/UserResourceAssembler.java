package com.saman.oak.portal.business.service.user;

import com.saman.oak.portal.controller.security.user.UserViewController;
import com.saman.oak.portal.domain.user.UserEntity;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by saman on 11/28/2017.
 */
@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<UserEntity, UserResource> {

//    @Autowired
//    private EntityLinks entityLinks;

    public UserResourceAssembler() {
        super(UserViewController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(UserEntity user) {
        UserResource resource = createResourceWithId(user.getId(), user);

        return resource;
    }

    @Override
    protected UserResource instantiateResource(UserEntity entity) {
        return new UserResource(entity);
    }
}
