package com.kakao.config;

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
@EnableJpaRepositories(entityManagerFactoryRef = "kamtecEntityManagerFactory", transactionManagerRef = "kamtecTransactionManager", basePackages = {"com.kakao.kamtec.mapper"})
public class KamtecSqlConfig {
	private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    public KamtecSqlConfig(JpaProperties jpaProperties, HibernateProperties hibernateProperties) {
        this.jpaProperties = jpaProperties;
        this.hibernateProperties = hibernateProperties; 
    }
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.kamtec")
    public DataSourceProperties kamtecDataSourceProperties() {
        return new DataSourceProperties();
    } 
    
//    @Bean
//    public DataSource kamtecDataSource(@Qualifier("kamtecDataSourceProperties") DataSourceProperties dataSourceProperties) {
//        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.kamtec")
    public DataSource kamtecDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
    
//    @Bean
//    public LocalContainerEntityManagerFactoryBean kamtecEntityManagerFactory(@Qualifier("kamtecDataSource") DataSource hubDataSource, EntityManagerFactoryBuilder builder) {
//        return builder.dataSource(hubDataSource).packages("com.kakao.domain")
//                .persistenceUnit("kamtec").build();
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean kamtecEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        var properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder.dataSource(kamtecDataSource())
        		.properties(properties)
        		.packages("com.kakao.domain")
                .persistenceUnit("kamtec")
                .build();
    }
    
//    @Bean
//    public PlatformTransactionManager kamtecTransactionManager(@Qualifier("kamtecEntityManagerFactory") EntityManagerFactory factory) {
//        return new JpaTransactionManager(factory);
//    }
    @Bean
    public PlatformTransactionManager kamtecTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(kamtecEntityManagerFactory(builder).getObject()));
    }
}
