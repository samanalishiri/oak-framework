package com.saman.oak.portal.domain.acl;

import com.saman.oak.core.domain.Constant;
import com.saman.oak.portal.SecurityConstant;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public interface AclObjectIdentityConstant extends SecurityConstant, Constant {
    String TABLE_NAME = "ACL_OBJECT_IDENTITY";
    String ENTITY_NAME = "aclObjectIdentityEntity";
    String MODEL_NAME = "aclObjectIdentityModel";

    String OBJECT_ID = "ObjectId";
    String ACL_CLASS = "aclClass";
    String PARENT = "parent";
    String OWNER = "owner";
    String ENTRIES_INHERITING = "entriesInheriting";
}
