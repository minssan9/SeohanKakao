package com.message.config;

import java.util.Objects;

//import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import lombok.var;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "seohanEntityManagerFactory", transactionManagerRef = "seohanTransactionManager", basePackages = {"com.message.seohan.mapper"})
public class SeohanSqlConfig { 
	private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    public SeohanSqlConfig(JpaProperties jpaProperties, HibernateProperties hibernateProperties) {
        this.jpaProperties = jpaProperties;
        this.hibernateProperties = hibernateProperties; 
    }
    
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.seohan")
    public DataSourceProperties seohanDataSourceProperties() {
        return new DataSourceProperties();
    }

//    @Bean
//    @Primary
//    public DataSource seohanDataSource(@Qualifier("seohanDataSourceProperties") DataSourceProperties dataSourceProperties) {
//        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.seohan")
    public DataSource seohanDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
    
//    @Bean
//    @Primary
//    public LocalContainerEntityManagerFactoryBean seohanEntityManagerFactory(@Qualifier("seohanDataSource") DataSource hubDataSource, EntityManagerFactoryBuilder builder) {
//        return builder.dataSource(hubDataSource)
//        		.packages("com.message.domain")
//                .persistenceUnit("seohan").build();
//    }
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean seohanEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        var properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder.dataSource(seohanDataSource())
        		.properties(properties)
        		.packages("com.message.mssql.domain")
                .persistenceUnit("seohan")
                .build();
    }
//    @Bean
//    @Primary
//    public PlatformTransactionManager seohanTransactionManager(@Qualifier("seohanEntityManagerFactory") EntityManagerFactory factory) {
//        return new JpaTransactionManager(factory);
//    }
    @Bean
    @Primary
    public PlatformTransactionManager seohanTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(seohanEntityManagerFactory(builder).getObject()));
    }
}
