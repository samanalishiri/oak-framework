package com.saman.oak.core.database.datasource;

import com.saman.oak.core.database.SpringDataSource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;

public class WeblogicSpringDataSource implements SpringDataSource {
    @Override
    public DataSource createDataSource(Environment properties) throws NamingException {
        JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
        factoryBean.setJndiName(properties.getRequiredProperty("datasource.jndi"));
        factoryBean.afterPropertiesSet();
        return DataSource.class.cast(factoryBean.getObject());
    }
}
