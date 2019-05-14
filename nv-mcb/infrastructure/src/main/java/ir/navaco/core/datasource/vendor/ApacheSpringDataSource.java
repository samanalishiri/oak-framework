package ir.navaco.core.datasource.vendor;

import ir.navaco.core.datasource.SpringDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.core.env.Environment;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public class ApacheSpringDataSource implements SpringDataSource {
    @Override
    public DataSource create(Environment properties) throws NamingException {
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
