package com.dat.miniATM.adapters.configuration.config;

import java.sql.SQLException;

import org.axonframework.common.jpa.EntityManagerProvider;

import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class EventStoreConfig {

    @Bean(name = "eventStorageEngineCustom")
    public EventStorageEngine eventStorageEngine(Serializer serializer,
                                                 DataSource dataSource,
                                                 EntityManagerProvider entityManagerProvider,
                                                 SpringTransactionManager transactionManager) throws SQLException {
        return JpaEventStorageEngine.builder()
                .snapshotSerializer(serializer)
                .eventSerializer(serializer)
                .dataSource(dataSource)
                .entityManagerProvider(entityManagerProvider)
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public SpringTransactionManager springTransactionManager(PlatformTransactionManager transactionManager) {
        return new SpringTransactionManager(transactionManager);
    }

    @Bean
    @Primary
    public EventStore eventStore(@Qualifier("eventStorageEngineCustom")EventStorageEngine storageEngine) {
        return EmbeddedEventStore.builder()
                .storageEngine(storageEngine)
                .build();
    }


}