package com.saman.oak.portal.domain.user;

import com.saman.oak.core.domain.Constant;
import com.saman.oak.portal.SecurityConstant;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public interface AddressConstant extends SecurityConstant, Constant {
    String TABLE_NAME = "ADDRESS";
    String ENTITY_NAME = "addressEntity";
    String MODEL_NAME = "addressModel";

    String COUNTRY = "country";
    String PROVINCE = "province";
    String CITY = "city";
    String ADDITIONAL = "additional";
    String ZIP_CODE = "zipCode";
}
