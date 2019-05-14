package ir.navaco.core.datasource.vendor;

import ir.navaco.core.datasource.SpringDataSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public class EmbeddedSpringDataSource implements SpringDataSource {
    @Override
    public DataSource create(Environment properties) throws NamingException {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.valueOf(properties.getRequiredProperty("embedded.database.type"))).build();
    }
}
