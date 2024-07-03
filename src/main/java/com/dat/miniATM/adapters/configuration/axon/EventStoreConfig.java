//package com.dat.miniATM.adapters.configuration.axon;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
//import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
//import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
//import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//public class EventStoreConfig {
//
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;
//
//    @Autowired
//    private PlatformTransactionManager transactionManager;
//
//    @Bean
//    public EventStorageEngine eventStorageEngine() {
//        return JpaEventStorageEngine.builder()
//                .entityManagerProvider(() -> entityManagerFactory.createEntityManager())
//                .transactionManager(transactionManager)
//                .build();
//    }
//
//    @Bean
//    public EmbeddedEventStore eventStore(EventStorageEngine storageEngine) {
//        return EmbeddedEventStore.builder()
//                .storageEngine(storageEngine)
//                .build();
//    }
//
//    @Bean
//    public SpringAggregateSnapshotterFactoryBean snapshotterFactoryBean() {
//        return new SpringAggregateSnapshotterFactoryBean();
//    }
//
//}