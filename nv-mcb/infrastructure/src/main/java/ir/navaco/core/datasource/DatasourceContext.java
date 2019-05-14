package ir.navaco.core.datasource;


import ir.navaco.core.datasource.vendor.ApacheSpringDataSource;
import ir.navaco.core.datasource.vendor.ApacheTomcatDataSource;
import ir.navaco.core.datasource.vendor.EmbeddedSpringDataSource;
import ir.navaco.core.datasource.vendor.JBossSpringDataSource;
import ir.navaco.core.datasource.vendor.WeblogicSpringDataSource;

import java.util.EnumMap;

import static ir.navaco.core.datasource.DataSourceVendor.EMBEDDED;
import static ir.navaco.core.datasource.DataSourceVendor.JBOSS;
import static ir.navaco.core.datasource.DataSourceVendor.SPRING;
import static ir.navaco.core.datasource.DataSourceVendor.TOMCAT;
import static ir.navaco.core.datasource.DataSourceVendor.WEBLOGIC;

/**
 * @author Saman Alishiri
 */
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
