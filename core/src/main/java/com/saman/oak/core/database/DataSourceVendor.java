package com.saman.oak.core.database;

/**
 * Created by saman on 8/31/2017.
 */
public enum DataSourceVendor {
    WEBLOGIC,
    JBOSS,
    SPRING,
    EMBEDDED,
    ;


    public static DataSourceVendor getDSVendor(String name) {
        return DataSourceVendor.valueOf(name.toUpperCase());
    }
}
