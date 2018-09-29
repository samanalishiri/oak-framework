package com.saman.oak.portal.business.service.user;

import com.saman.oak.core.converter.Converter;
import com.saman.oak.portal.domain.user.UserEntity;

public class UserConverter extends Converter<UserEntity, UserModel> {
    @Override
    public UserModel convert(UserEntity userEntity, int deep, String... relations) {
        return null;
    }

    @Override
    public UserEntity convert(UserModel userModel, int deep, String... relations) {
        return null;
    }
}
