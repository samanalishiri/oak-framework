package com.saman.oak.portal.business.service.user;

import com.saman.oak.portal.controller.security.user.UserViewController;
import com.saman.oak.portal.domain.user.UserEntity;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@Component(UserResourceAssembler.NAME)
public class UserResourceAssembler extends ResourceAssemblerSupport<UserEntity, UserResource> {

    public static final String NAME = "serResourceAssembler";
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
