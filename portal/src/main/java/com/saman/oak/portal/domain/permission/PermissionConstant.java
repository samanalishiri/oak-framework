package com.saman.oak.portal.domain.permission;

import com.saman.oak.core.domain.Constant;
import com.saman.oak.portal.SecurityConstant;

public interface PermissionConstant extends SecurityConstant, Constant {
    String TABLE_NAME = "PERMISSION";
    String ENTITY_NAME = "permissionEntity";
    String MODEL_NAME = "permissionModel";

    String NAME = "name";
    String DESCRIPTION = "description";
    String ENABLED = "enabled";
    String AUTHORITIES = "authorities";
    String PARENT = "PARENT";
    String CHILDREN = "CHILDREN";
}
