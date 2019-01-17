package com.saman.oak.core.database;

import org.springframework.core.env.Environment;

import javax.naming.NamingException;
import javax.sql.DataSource;

public interface SpringDataSource {
    DataSource create(Environment properties) throws NamingException;
}
