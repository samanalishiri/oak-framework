package com.saman.oak.portal.domain.acl;

import com.saman.oak.core.domain.Constant;
import com.saman.oak.portal.SecurityConstant;

public interface AclClassConstant extends SecurityConstant, Constant {
    String TABLE_NAME = "ACL_CLASS";
    String ENTITY_NAME = "aclClassEntity";
    String MODEL_NAME = "aclClassModel";

    String SID = "sid";
    String PRINCIPAL = "principal";
}
