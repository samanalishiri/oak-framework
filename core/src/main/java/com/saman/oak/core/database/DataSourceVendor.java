package com.saman.oak.core.database;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
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
