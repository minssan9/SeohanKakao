package com.message.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import lombok.var;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "db2EntityManagerFactory", transactionManagerRef = "db2TransactionManager", basePackages = {"com.message.db2.mapper"})
public class DB2SqlConfig {
	private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    public DB2SqlConfig(JpaProperties jpaProperties, HibernateProperties hibernateProperties) {
        this.jpaProperties = jpaProperties;
        this.hibernateProperties = hibernateProperties; 
    }
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSourceProperties db2DataSourceProperties() {
        return new DataSourceProperties();
    } 
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    } 

    @Bean
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        var properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder.dataSource(db2DataSource())
        		.properties(properties)
        		.packages("com.message.domain")
                .persistenceUnit("db2")
                .build();
    }
    
    @Bean
    public PlatformTransactionManager db2TransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(db2EntityManagerFactory(builder).getObject()));
    }
}
