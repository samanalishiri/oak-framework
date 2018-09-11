package com.saman.oak.portal.domain.user;

import com.saman.oak.core.protocol.RefData;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public enum DeviceType implements RefData {
    PHONE(1, "enum.deviceType.phone"),
    MOBILE(2, "enum.deviceType.mobile"),
    FAX(3, "enum.deviceType.fax"),
    VOIP(4, "enum.deviceType.voip"),
    EMAIL(5, "enum.deviceType.email"),
    SOCIAL_NETWORK(6, "enum.deviceType.socialNetwork"),
    ;

    private final Integer id;
    private final String value;

    DeviceType(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public String value() {
        return value;
    }
}
