package com.saman.oak.core.database;

import com.saman.oak.core.database.datasource.ApacheSpringDataSource;
import com.saman.oak.core.database.datasource.EmbeddedSpringDataSource;
import com.saman.oak.core.database.datasource.JBossSpringDataSource;
import com.saman.oak.core.database.datasource.WeblogicSpringDataSource;

import java.util.HashMap;
import java.util.Map;

public class DatasourceContext {

    private static Map<DataSourceVendor, SpringDataSource> dataSourceHolder;

    static {
        dataSourceHolder = new HashMap() {{
            put(DataSourceVendor.WEBLOGIC, new WeblogicSpringDataSource());
            put(DataSourceVendor.JBOSS, new JBossSpringDataSource());
            put(DataSourceVendor.SPRING, new ApacheSpringDataSource());
            put(DataSourceVendor.EMBEDDED, new EmbeddedSpringDataSource());
        }};
    }

    public static SpringDataSource get(DataSourceVendor vendor) {
        return dataSourceHolder.get(vendor);
    }

}
