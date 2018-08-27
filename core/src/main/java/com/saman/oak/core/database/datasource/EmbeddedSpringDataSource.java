package com.saman.oak.core.database.datasource;

import com.saman.oak.core.database.SpringDataSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmbeddedSpringDataSource implements SpringDataSource {
    @Override
    public DataSource createDataSource(Environment properties) throws NamingException {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.valueOf(properties.getRequiredProperty("embedded.database.type"))).build();
    }
}
