package ir.navaco.core.datasource.vendor;

import ir.navaco.core.datasource.SpringDataSource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public class WeblogicSpringDataSource implements SpringDataSource {
    @Override
    public DataSource create(Environment properties) throws NamingException {
        JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
        factoryBean.setJndiName(properties.getRequiredProperty("datasource.jndi"));
        factoryBean.afterPropertiesSet();
        return DataSource.class.cast(factoryBean.getObject());
    }
}
