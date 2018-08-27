package com.saman.oak.core.database.datasource;

import com.saman.oak.core.database.SpringDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.core.env.Environment;

import javax.naming.NamingException;
import javax.sql.DataSource;

public class ApacheSpringDataSource implements SpringDataSource {
    @Override
    public DataSource createDataSource(Environment properties) throws NamingException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(properties.getRequiredProperty("jdbc.driver"));
        dataSource.setUrl(properties.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(properties.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(properties.getRequiredProperty("jdbc.password"));
        dataSource.setMaxIdle(properties.getRequiredProperty("jdbc.max.idle", Integer.class));
        dataSource.setMaxActive(properties.getRequiredProperty("jdbc.max.active", Integer.class));
        return dataSource;
    }
}
