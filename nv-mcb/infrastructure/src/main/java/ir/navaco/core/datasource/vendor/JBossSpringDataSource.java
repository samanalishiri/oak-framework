package ir.navaco.core.datasource.vendor;

import ir.navaco.core.datasource.SpringDataSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public class JBossSpringDataSource implements SpringDataSource {
    @Override
    public DataSource create(Environment properties) throws NamingException {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        dataSourceLookup.setResourceRef(true);
        return dataSourceLookup.getDataSource(properties.getRequiredProperty("datasource.jndi"));
    }
}
