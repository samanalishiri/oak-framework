package com.saman.oak.portal.domain.user;

import com.saman.oak.core.domain.Constant;
import com.saman.oak.portal.SecurityConstant;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public interface UserConstant extends SecurityConstant, Constant {
    String TABLE_NAME = "USER_INFO";
    String ENTITY_NAME = "userEntity";
    String MODEL_NAME = "userModel";

    String USERNAME = "username";
    String PASSWORD = "password";
    String EMAIL = "email";
    String ENABLED = "enabled";
    String ACCOUNT_NON_EXPIRED = "accountNonExpired";
    String ACCOUNT_NON_LOCKED = "accountNonLocked";
    String CREDENTIALS_NON_EXPIRED = "credentialsNonExpired";
    String PERSON = "person";
    String AUTHORITIES = "authorities";
}
