package com.saman.oak.portal.business.service.user;

import com.saman.oak.core.converter.Converter;
import com.saman.oak.portal.business.service.authority.AuthorityConverter;
import com.saman.oak.portal.domain.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(UserConverter.NAME)
public class UserConverter extends Converter<UserEntity, UserModel> {

    public static final String NAME = "userConverter";

    @Autowired
    private AuthorityConverter authorityConverter;


    @Override
    public void internal(UserEntity input, UserModel output, int deep, String... relations) {
        output.setId(input.getId())
                .setUsername(input.getUsername())
                .setPassword(input.getPassword())
                .setEmail(input.getEmail())
                .setAuthorities(authorityConverter.convertEntities(input.getAuthorities(), 1));
    }

    @Override
    public void internal(UserModel input, UserEntity output, int deep, String... relations) {
        output.setId(input.getId())
                .setUsername(input.getUsername())
                .setPassword(input.getPassword())
                .setEmail(input.getEmail())
                .setAuthorities(authorityConverter.convertModels(input.getAuthorities(), 1));
    }

    @Override
    public UserEntity newEntity() {
        return new UserEntity();
    }

    @Override
    public UserModel newModel() {
        return new UserModel();
    }
}
