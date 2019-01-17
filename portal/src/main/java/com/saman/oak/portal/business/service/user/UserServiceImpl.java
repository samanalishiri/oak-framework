package com.saman.oak.portal.business.service.user;

import com.saman.oak.core.business.hateoas.service.ResourceableCompleteService;
import com.saman.oak.core.converter.Converter;
import com.saman.oak.core.naming.BusinessServiceConstant;
import com.saman.oak.portal.business.repository.user.UserRepositoty;
import com.saman.oak.portal.business.service.ResourceableServiceProxy;
import com.saman.oak.portal.domain.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends ResourceableServiceProxy<Long, UserModel, UserEntity, UserResource, UserRepositoty> implements UserService {

    @Autowired
    @Qualifier("bCryptPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Order(1)
    private UserRepositoty repository;

    @Autowired
    @Qualifier(UserConverter.NAME)
    @Order(1)
    private Converter<UserEntity, UserModel> converter;

    @Autowired
    @Order(1)
    @Qualifier(UserResourceAssembler.NAME)
    private ResourceAssemblerSupport<UserEntity, UserResource> assembler;

    @Autowired
    @Qualifier(BusinessServiceConstant.RESOURCEABLE_SPRING_SERVICE)
    @Order(2)
    @Override
    public void setService(ResourceableCompleteService<Long, UserModel, UserEntity, UserResource, UserRepositoty> service) {
        service.setRepository(repository);
        service.setConverter(converter);
        service.setAssembler(assembler);
        service.beforeSaveRules(beforeSaveRules());
        super.setService(service);
    }

    private Consumer<UserModel>[] beforeSaveRules() {
        Consumer<UserModel> encodePasswordRule = model -> model.setPassword(passwordEncoder.encode(model.getPassword()));
        return Stream.of(encodePasswordRule).toArray(Consumer[]::new);
    }

}
