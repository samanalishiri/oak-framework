package com.saman.oak.core.database.datasource;

import com.saman.oak.core.database.SpringDataSource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;

public class ApacheTomcatDataSource implements SpringDataSource {
    @Override
    public DataSource createDataSource(Environment properties) throws NamingException {
        return (DataSource) new JndiTemplate().lookup(properties.getProperty("datasource.jndi"));
    }
}
