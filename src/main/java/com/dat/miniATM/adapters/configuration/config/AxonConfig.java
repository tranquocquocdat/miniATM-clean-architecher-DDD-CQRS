package com.dat.miniATM.adapters.configuration.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.jpa.SimpleEntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public EntityManagerProvider entityManagerProvider() {
        return new SimpleEntityManagerProvider(entityManager);
    }

//    @Bean
//    public EventStorageEngine eventStorageEngine(EntityManagerProvider entityManagerProvider, Serializer serializer) {
//        return JpaEventStorageEngine.builder()
//                .entityManagerProvider(entityManagerProvider)
//                .build();
//    }
}