package com.saman.oak.core.database.datasource;

import com.saman.oak.core.database.SpringDataSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.naming.NamingException;
import javax.sql.DataSource;

public class JBossSpringDataSource implements SpringDataSource {
    @Override
    public DataSource create(Environment properties) throws NamingException {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        dataSourceLookup.setResourceRef(true);
        return dataSourceLookup.getDataSource(properties.getRequiredProperty("datasource.jndi"));
    }
}
