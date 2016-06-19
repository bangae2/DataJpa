package com.springapp.config.webconfig;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by bangae1 on 2016-06-13.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.springapp.**.repository"})
@PropertySource("classpath:application.properties")
public class DataSourceConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("jdbc.maxActive")));
        dataSource.setMaxIdle(Integer.parseInt(env.getProperty("jdbc.maxIdle")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("jdbc.minIdle")));
        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPackagesToScan(new String[] {"com.springapp.**.entity"});
        entityManagerFactoryBean.setDataSource(dataSource());
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

        LoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
        entityManagerFactoryBean.setLoadTimeWeaver(loadTimeWeaver);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        jpaProperties.put("hibernate.enable_lazy_load_no_trans", env.getProperty("hibernate.enable_lazy_load_no_trans"));
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager platformTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
