package com.saman.oak.portal.domain.user;

import com.saman.oak.core.domain.Constant;
import com.saman.oak.portal.SecurityConstant;


/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public interface DeviceTypeConstant extends SecurityConstant, Constant {
    String TABLE_NAME = "DEVICE_TYPE";
    String ENTITY_NAME = "deviceTypeEntity";
    String MODEL_NAME = "deviceTypeModel";

    String NAME = "name";
    String DESCRIPTION = "description";
    String ENABLED = "enabled";
}
