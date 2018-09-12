package com.saman.oak.portal.domain.acl;

import com.saman.oak.core.domain.Constant;
import com.saman.oak.portal.SecurityConstant;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public interface AclEntryConstant extends SecurityConstant, Constant {
    String TABLE_NAME = "ACL_ENTRY";
    String ENTITY_NAME = "aclEntryEntity";
    String MODEL_NAME = "aclEntryModel";

    String ACL_OBJECT_IDENTITY = "aclObjectIdentity";
    String ACE_ORDER = "aceOrder";
    String SID = "sid";
    String MASK = "mask";
    String GRANTING = "granting";
    String AUDIT_SUCCESS = "auditSuccess";
    String AUDIT_FAILURE = "auditFailure";
}
