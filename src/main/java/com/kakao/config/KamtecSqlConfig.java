package com.kakao.config;

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
@EnableJpaRepositories(entityManagerFactoryRef = "kamtecEntityManagerFactory", transactionManagerRef = "kamtecTransactionManager", basePackages = {"com.kakao.kamtec.mapper"})
public class KamtecSqlConfig {

    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.kamtec")
    public DataSourceProperties kamtecDataSourceProperties() {
        return new DataSourceProperties();
    }

    
    @Bean
    public DataSource kamtecDataSource(@Qualifier("kamtecDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    
    @Bean
    public LocalContainerEntityManagerFactoryBean kamtecEntityManagerFactory(@Qualifier("kamtecDataSource") DataSource hubDataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(hubDataSource).packages("com.kamtec.kakao.domain")
                .persistenceUnit("kamtec").build();
    }

    
    @Bean
    public PlatformTransactionManager kamtecTransactionManager(@Qualifier("kamtecEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
