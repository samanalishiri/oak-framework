package com.saman.oak.portal.domain.user;

import com.saman.oak.core.domain.Constant;
import com.saman.oak.portal.SecurityConstant;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public interface DeviceConstant extends SecurityConstant, Constant {
    String TABLE_NAME = "DEVICE";
    String ENTITY_NAME = "deviceEntity";
    String MODEL_NAME = "deviceModel";

    String PREFIX = "prefix";
    String IDENTITY = "identity";

}
