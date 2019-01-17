package com.saman.oak.core.database;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */

public enum DataSourceVendor {
    WEBLOGIC,
    JBOSS,
    SPRING,
    EMBEDDED,
    TOMCAT,
    ;

    public static DataSourceVendor getDSVendor(String name) {
        return DataSourceVendor.valueOf(name.toUpperCase());
    }
}
