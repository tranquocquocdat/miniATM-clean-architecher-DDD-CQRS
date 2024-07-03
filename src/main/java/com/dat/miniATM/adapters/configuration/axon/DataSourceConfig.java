package com.dat.miniATM.adapters.configuration.axon;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EventStoreConfig {

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
            @Qualifier("eventStoreDataSource") DataSource accountDataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(accountDataSource)
                .packages("com.iij.account.database", "com.iij.asp.sl2.common.database.infrastructure.datasource")
                .persistenceUnit("account").properties(jpaProperties()).build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager accountTransactionManager(
            @Qualifier("accountEmFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }

    private Map<String, Object> jpaProperties() {
        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.show_sql", true);
        jpaProperties.put("hibernate.format_sql", true);
        jpaProperties.put("spring.jpa.hibernate.ddl-auto", "update");
        return jpaProperties;
    }
}
