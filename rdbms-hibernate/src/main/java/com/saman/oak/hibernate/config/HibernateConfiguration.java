package com.saman.oak.hibernate.config;

import com.saman.oak.core.database.DatasourceContext;
import com.saman.oak.core.orm.ConnectionProperties;
import com.saman.oak.core.properties.EnvironmentHelper;
import com.saman.oak.core.properties.PropertiesHelper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.saman.oak.core.database.DataSourceVendor.getDSVendor;
import static com.saman.oak.core.orm.ConnectionProperties.getConnectionProperties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"resources/config/db/database.properties"})
public class HibernateConfiguration {

    private EnvironmentHelper env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = new EnvironmentHelper(env);
    }

    @Bean(name = "dataSource", destroyMethod = "")
    public DataSource getDataSource() throws NamingException {
        return DatasourceContext.get(getDSVendor(env.value("datasource.vendor"))).createDataSource(env.get());
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() throws NamingException {

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();

        if (!env.booleanValue("datasource.init_sql_file"))
            return null;

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        Resource[] resources = new Resource[2];

        if (env.has("datasource.init_schema_file_name"))
            resources[0] = new ClassPathResource("/" + env.value("datasource.init_schema_file_name"));

        if (env.has("datasource.init_data_file_name"))
            resources[1] = new ClassPathResource("/" + env.value("datasource.init_data_file_name"));

        resourceDatabasePopulator.addScripts(resources);
        dataSourceInitializer.setDataSource(getDataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);

        return dataSourceInitializer;
    }

    private Properties hibernateProperties() {
        ConnectionProperties cp = getConnectionProperties(env.value("hibernate.session_factory.vendor"));
        return PropertiesHelper.NEW()
                .put("hibernate.dialect", env.value("hibernate.dialect"))
                .put("hibernate.show_sql", env.value("hibernate.show_sql"))
                .put("hibernate.current_session_context_class", env.value("hibernate.current_session_context_class"))
                .put("hibernate.hbm2ddl.auto", env.value("hibernate.hbm2ddl.auto"))
                .put("hibernate.max_fetch_depth", env.value("hibernate.max.fetch.depth"))
                .put("hibernate.cache.use_second_level_cache", env.value("hibernate.use.second.level.cache"))
                .put(cp.keys().stream().filter(env::has).collect(Collectors.toMap(cp::value, env::value)))
                .get();
    }

    @Autowired
    @Qualifier("dataSource")
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[]{env.value("domain.package")});
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.afterPropertiesSet();
        return sessionFactory.getObject();
    }

    @Autowired
    @Qualifier("sessionFactory")
    @Bean(name = "transactionManager")
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
