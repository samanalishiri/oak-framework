package com.saman.oak.portal.config;

import com.saman.oak.core.database.DatasourceContext;
import com.saman.oak.core.orm.ConnectionProperties;
import com.saman.oak.core.properties.EnvironmentHelper;
import com.saman.oak.core.properties.PropertiesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.saman.oak.core.database.DataSourceVendor.getDSVendor;
import static com.saman.oak.core.orm.ConnectionProperties.getConnectionProperties;

/**
 * Created by saman on 11/29/2017.
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.saman.oak")
@EnableSpringDataWebSupport
@PropertySource(value = {"resources/config/db/database.properties"})
@EnableEntityLinks
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class SpringDataJpaConfiguration {

    private EnvironmentHelper env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = new EnvironmentHelper(env);
    }

    @Bean(name = "dataSource", destroyMethod = "")
    public DataSource getDataSource() throws NamingException {
        return DatasourceContext.get(getDSVendor(env.value("datasource.vendor"))).createDataSource(env.get());
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

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() throws NamingException {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(new String[]{env.value("domain.package")});
        factory.setDataSource(getDataSource());
        factory.setJpaProperties(hibernateProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() throws SQLException, NamingException {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

}
