package com.message.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.var;
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

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "kamtecEntityManagerFactory", transactionManagerRef = "kamtecTransactionManager", basePackages = {"com.message.mapper.kamtec"})
public class KamtecSqlConfig {
	private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    public KamtecSqlConfig(JpaProperties jpaProperties, HibernateProperties hibernateProperties) {
        this.jpaProperties = jpaProperties;
        this.hibernateProperties = hibernateProperties; 
    }
    
    @Bean
    public DataSourceProperties kamtecDataSourceProperties() {
        return new DataSourceProperties();
    } 

    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.kamtec")
    public DataSource kamtecDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean kamtecEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        var properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder.dataSource(kamtecDataSource())
        		.properties(properties)
        		.packages("com.message.domain")
                .persistenceUnit("kamtecEntityManager")
                .build();
    }

    @Bean
    public PlatformTransactionManager kamtecTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(kamtecEntityManagerFactory(builder).getObject()));
    }
}
