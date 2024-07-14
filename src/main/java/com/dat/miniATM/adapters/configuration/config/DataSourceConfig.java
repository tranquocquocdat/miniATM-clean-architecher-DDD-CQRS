package com.dat.miniATM.adapters.configuration.config;

import com.zaxxer.hikari.HikariDataSource;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "eventStoreEmFactory", transactionManagerRef = "transactionManager", basePackages = {
        "com.dat.miniATM.adapters.out.persistence.repositories"})
public class DataSourceConfig {

    @Primary
    @ConfigurationProperties("spring.datasource.event-store")
    @Order(1)
    @Bean
    DataSourceProperties eventStoreProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @DependsOn("eventStoreProperties")
    @Order(2)
    DataSource eventStoreDataSource(@Qualifier("eventStoreProperties") DataSourceProperties dataSourceProperties) {
        HikariDataSource dataSource = dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
        dataSource.setMaximumPoolSize(2);
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean eventStoreEmFactory(
            @Qualifier("eventStoreDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource)
                .packages("com.dat.miniATM.adapters.out.persistence.entities",
                        "org.axonframework.eventsourcing.eventstore.jpa",
                        "org.axonframework.eventhandling.deadletter.jpa",
                        "org.axonframework.eventhandling.tokenstore.jpa")
                .persistenceUnit("eventStore").properties(jpaProperties()).build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(
            @Qualifier("eventStoreEmFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }

    private Map<String, Object> jpaProperties() {
        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.show_sql", true);
        jpaProperties.put("hibernate.format_sql", true);
        jpaProperties.put("hibernate.hbm2ddl.auto", "update"); // Thay đổi thuộc tính này
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); // Thêm dòng này nếu chưa có
        return jpaProperties;
    }
}
