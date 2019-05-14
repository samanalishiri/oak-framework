package ir.navaco.core.config.orm;

import ir.navaco.core.datasource.DatasourceContext;
import ir.navaco.core.datasource.HibernateConnectionProperties;
import ir.navaco.core.util.EnvironmentHelper;
import ir.navaco.core.util.PropertiesBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.stream.Collectors;

import static ir.navaco.core.datasource.DataSourceVendor.getDSVendor;
import static ir.navaco.core.datasource.HibernateConnectionProperties.getConnectionProperties;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = {"resources/db/database.properties"})
public class SpringDataJpaConfiguration {

    private final Logger logger = LoggerFactory.getLogger(SpringDataJpaConfiguration.class);

    private EnvironmentHelper env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = new EnvironmentHelper(env);
    }

    @Bean(name = "dataSource", destroyMethod = "")
    public DataSource getDataSource() throws NamingException {
        return DatasourceContext.get(getDSVendor(env.value("datasource.vendor"))).create(env.get());
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() throws NamingException {

        if (!env.booleanValue("datasource.init_sql_file"))
            return null;

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.setIgnoreFailedDrops(true);

        if (env.has("datasource.init_schema_file_name"))
            resourceDatabasePopulator.addScript(new ClassPathResource("/" + env.value("datasource.init_schema_file_name")));

        if (env.has("datasource.init_data_file_name"))
            resourceDatabasePopulator.addScript(new ClassPathResource("/" + env.value("datasource.init_data_file_name")));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(getDataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);

        return dataSourceInitializer;
    }

    private Properties hibernateProperties() {
        HibernateConnectionProperties cp = getConnectionProperties(env.value("hibernate.session_factory.vendor"));
        return PropertiesBuilder.NEW()
                .put("hibernate.dialect", env.value("hibernate.dialect"))
                .put("hibernate.show_sql", env.value("hibernate.show_sql"))
                .put("hibernate.current_session_context_class", env.value("hibernate.current_session_context_class"))
                .put("hibernate.hbm2ddl.auto", env.value("hibernate.hbm2ddl.auto"))
                .put("hibernate.max_fetch_depth", env.value("hibernate.max.fetch.depth"))
                .put("hibernate.cache.use_second_level_cache", env.value("hibernate.use.second.level.cache"))
                .put(cp.keys().stream().filter(env::has).collect(Collectors.toMap(cp::value, env::value)))
                .get();
    }

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() throws NamingException {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(env.values("domain.package"));
        factory.setDataSource(getDataSource());
        factory.setJpaProperties(hibernateProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() throws NamingException {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

}