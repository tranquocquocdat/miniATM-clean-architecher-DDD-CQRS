package com.dat.miniATM.adapters.configuration.config;

import java.sql.SQLException;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class AxonConfig {

    @Bean(name = "eventStorageEngineCustom")
    public EventStorageEngine eventStorageEngine(Serializer serializer,
                                                 DataSource dataSource,
                                                 EntityManagerProvider entityManagerProvider,
                                                 PlatformTransactionManager transactionManager) throws SQLException {
        SpringTransactionManager axonTransactionManager = new SpringTransactionManager(transactionManager);
        return JpaEventStorageEngine.builder()
                .snapshotSerializer(serializer)
                .eventSerializer(serializer)
                .dataSource(dataSource)
                .entityManagerProvider(entityManagerProvider)
                .transactionManager(axonTransactionManager)
                .build();
    }

    @Bean
    public TokenStore tokenStore(EntityManagerProvider entityManagerProvider, Serializer serializer) {
        return JpaTokenStore.builder()
                .entityManagerProvider(entityManagerProvider)
                .serializer(serializer)
                .build();
    }

}