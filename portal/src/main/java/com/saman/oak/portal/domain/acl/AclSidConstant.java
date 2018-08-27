package com.saman.oak.portal.domain.acl;

import com.saman.oak.core.domain.Constant;
import com.saman.oak.portal.SecurityConstant;

public interface AclSidConstant extends SecurityConstant, Constant {
    String TABLE_NAME = "ACL_SID";
    String ENTITY_NAME = "aclSidEntity";
    String MODEL_NAME = "aclSidModel";

    String SID = "sid";
    String PRINCIPAL = "principal";
}
