package ir.navaco.core.datasource;

import org.springframework.core.env.Environment;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author Saman Alishiri
 */
public interface SpringDataSource {
    DataSource create(Environment properties) throws NamingException;
}
