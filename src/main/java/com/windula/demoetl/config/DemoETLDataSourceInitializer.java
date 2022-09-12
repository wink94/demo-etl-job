package com.windula.demoetl.config;


import com.windula.demoetl.exception.ConfigurationException;
import com.windula.demoetl.exception.ExceptionEnum;
import com.windula.demoetl.exception.ConfigurationException;
import com.windula.demoetl.exception.ExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "demoetlEntityManager",
        transactionManagerRef = "demoetlTransactionManager",
        basePackages = "com.windula.demoetl.dao"
)
@PropertySource("classpath:application.properties")
public class DemoETLDataSourceInitializer extends DataSourceInitializer{

    @Value("${databasePort}")
    private int databasePort;
    private static final String persistenceUnitName = "demoetlMysql";
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoETLDataSourceInitializer.class);

    public DemoETLDataSourceInitializer() {
    }

    @Bean(name = "demoetlDataSource", destroyMethod = "close")
    @Primary
    @DependsOn({"configurationProvider"})
    public DataSource dataSource(@Qualifier("configurationProvider")ConfigurationProvider configurationProvider) {

        try {

            return getDataSource(
                    configurationProvider.getHost(),
                    configurationProvider.getDatabase(),
                    configurationProvider.getUser(),
                    configurationProvider.getPassword(),
                    databasePort,
                    false);

        } catch (NullPointerException npe) {

            LOGGER.error("error: demoetl DataSourceInitializer database failed", npe);
            throw new ConfigurationException(ExceptionEnum.DATABASE_PROPERTIES_FETCH);
        }

    }


    @Bean(name = "demoetlPersistenceUnitManager")
    @Primary
    @DependsOn({"demoetlDataSource"})
    public DefaultPersistenceUnitManager persistenceUnitManager(@Qualifier("demoetlDataSource") DataSource dataSource) {
        return getDefaultPersistenceUnitManager(dataSource, persistenceUnitName);
    }

    @Bean(name = "demoetlEntityManager")
    @Primary
    @DependsOn({"demoetlPersistenceUnitManager"})
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(@Qualifier("demoetlDataSource") DataSource dataSource,
                                                                           @Qualifier("demoetlPersistenceUnitManager") DefaultPersistenceUnitManager defaultPersistenceUnitManager) {
        return getEntityManagerFactoryBean(persistenceUnitName, defaultPersistenceUnitManager, dataSource);
    }

    @Bean(name = "demoetlTransactionManager")
    @Primary
    @DependsOn({"demoetlEntityManager"})
    public JpaTransactionManager jpaTransactionManager(@Qualifier("demoetlEntityManager") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return getJpaTransactionManager(localContainerEntityManagerFactoryBean);
    }
}
