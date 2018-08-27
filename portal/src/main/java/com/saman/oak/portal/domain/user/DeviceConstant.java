package com.saman.oak.portal.domain.user;

import com.saman.oak.core.domain.Constant;
import com.saman.oak.portal.SecurityConstant;

/**
 * Created by Administrator on 9/29/2017.
 */
public interface DeviceConstant extends SecurityConstant, Constant {
    String TABLE_NAME = "DEVICE";
    String ENTITY_NAME = "deviceEntity";
    String MODEL_NAME = "deviceModel";

    String PREFIX = "prefix";
    String IDENTITY = "identity";

}
