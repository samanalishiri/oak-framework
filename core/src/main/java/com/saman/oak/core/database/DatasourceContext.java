package com.saman.oak.core.database;

import com.saman.oak.core.database.datasource.ApacheSpringDataSource;
import com.saman.oak.core.database.datasource.ApacheTomcatDataSource;
import com.saman.oak.core.database.datasource.EmbeddedSpringDataSource;
import com.saman.oak.core.database.datasource.JBossSpringDataSource;
import com.saman.oak.core.database.datasource.WeblogicSpringDataSource;

import java.util.EnumMap;

import static com.saman.oak.core.database.DataSourceVendor.EMBEDDED;
import static com.saman.oak.core.database.DataSourceVendor.JBOSS;
import static com.saman.oak.core.database.DataSourceVendor.SPRING;
import static com.saman.oak.core.database.DataSourceVendor.TOMCAT;
import static com.saman.oak.core.database.DataSourceVendor.WEBLOGIC;

public class DatasourceContext {

    private static EnumMap<DataSourceVendor, SpringDataSource> dataSourceHolder = new EnumMap<DataSourceVendor, SpringDataSource>(DataSourceVendor.class) {{
        put(WEBLOGIC, new WeblogicSpringDataSource());
        put(JBOSS, new JBossSpringDataSource());
        put(SPRING, new ApacheSpringDataSource());
        put(EMBEDDED, new EmbeddedSpringDataSource());
        put(TOMCAT, new ApacheTomcatDataSource());
    }};

    public static SpringDataSource get(DataSourceVendor vendor) {
        return dataSourceHolder.get(vendor);
    }

}
