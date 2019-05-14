package ir.navaco.core.datasource.vendor;

import ir.navaco.core.datasource.SpringDataSource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public class ApacheTomcatDataSource implements SpringDataSource {
    @Override
    public DataSource create(Environment properties) throws NamingException {
        return (DataSource) new JndiTemplate().lookup(properties.getProperty("datasource.jndi"));
    }
}
