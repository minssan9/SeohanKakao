package com.kakao.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
 
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "seohanEntityManagerFactory", transactionManagerRef = "seohanTransactionManager", basePackages = {"com.sma.backend.multidb.database.seohan.repository"})

public class SeohanSqlConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.seohan")
    public DataSourceProperties seohanDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource seohanDataSource(@Qualifier("seohanDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean seohanEntityManagerFactory(@Qualifier("seohanDataSource") DataSource hubDataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(hubDataSource).packages("com.kakao.seohan.domain")
                .persistenceUnit("seohan").build();
    }

    @Bean
    public PlatformTransactionManager seohanTransactionManager(@Qualifier("seohanEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
