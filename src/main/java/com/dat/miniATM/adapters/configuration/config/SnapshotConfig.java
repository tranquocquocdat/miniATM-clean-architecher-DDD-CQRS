package com.dat.miniATM.adapters.configuration.config;


import com.dat.miniATM.domain.aggregates.AccountAggregate;
import org.axonframework.config.AggregateConfigurer;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventsourcing.*;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotter;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Collections;

@Configuration
public class SnapshotConfig {
    @Bean
    public AggregateFactory<AccountAggregate> accountAggregateFactory() {
        return new GenericAggregateFactory<>(AccountAggregate.class);
    }

    @Bean
    public Snapshotter snapshotter(EventStore eventStore, PlatformTransactionManager transactionManager) {
        return SpringAggregateSnapshotter.builder()
                .eventStore(eventStore)
                .transactionManager(new SpringTransactionManager(transactionManager))
                .aggregateFactories(Collections.singletonList(accountAggregateFactory()))
                .build();
    }

    @Bean(name = "snapshotTriggerDefinition")
    public SnapshotTriggerDefinition snapshotTriggerDefinition(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 1); // Số sự kiện trước khi tạo snapshot
    }

    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer.registerEventHandler(config -> accountAggregateFactory());
    }
}



