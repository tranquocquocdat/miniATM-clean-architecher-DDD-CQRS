package com.dat.miniATM.adapters.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.config.ConfigurerModule;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventhandling.deadletter.MongoSequencedDeadLetterQueue;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonMongoConfig {


    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://root:rootpassword@localhost:27017");
    }

    @Bean
    public MongoTemplate axonMongoTemplate() {
        return DefaultMongoTemplate.builder()
                .mongoDatabase(mongoClient(), "axon")
                .build();
    }

    @Bean
    public SpringMongoTemplate axonMongoTemplate() {
        return DefaultMongoTemplate.builder()
                .mongoDatabase(mongoClient(), "axon")
                .build();
    }

    @Bean
    public EventStorageEngine eventStorageEngine(MongoTemplate axonMongoTemplate) {
        return MongoEventStorageEngine.builder()
                .mongoTemplate(axonMongoTemplate)
                .build();
    }
    @Bean
    public SnapshotTriggerDefinition buyerAggregateSnapshotTrigger(
            Snapshotter snapshotter
    ) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 1);
    }

    @Bean
    public ConfigurerModule deadLetterQueueConfigurerModule(
            MongoTemplate mongoTemplate
    ) {
        // Replace "my-processing-group" for the processing group you want to configure the DLQ on.
        return configurer -> configurer.eventProcessing().registerDeadLetterQueue(
                "my-processing-group",
                config -> MongoSequencedDeadLetterQueue.builder()
                        .processingGroup("my-processing-group")
                        .maxSequences(256)
                        .maxSequenceSize(256)
                        .mongoTemplate(mongoTemplate)
                        .transactionManager(config.getComponent(TransactionManager.class))
                        .serializer(config.serializer())
                        .build()
        );
    }
}