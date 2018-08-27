package com.saman.oak.core.database;

import org.springframework.core.env.Environment;

import javax.naming.NamingException;
import javax.sql.DataSource;

public interface SpringDataSource {
    DataSource createDataSource(Environment properties) throws NamingException;
}
