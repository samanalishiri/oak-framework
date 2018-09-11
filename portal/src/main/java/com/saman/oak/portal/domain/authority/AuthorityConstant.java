package com.saman.oak.portal.domain.authority;

import com.saman.oak.core.domain.Constant;
import com.saman.oak.portal.SecurityConstant;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 *
 */

public interface AuthorityConstant extends SecurityConstant, Constant {
    String TABLE_NAME = "AUTHORITY";
    String ENTITY_NAME = "authorityEntity";
    String MODEL_NAME = "authorityModel";

    String AUTHORITY = "authority";
    String DESCRIPTION = "description";
    String ENABLED = "enabled";
    String USERS = "users";
    String PERMISSIONS = "permissions";
    String PARENT = "PARENT";
    String CHILDREN = "CHILDREN";
}
