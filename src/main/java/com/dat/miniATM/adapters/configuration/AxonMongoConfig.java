package com.dat.miniATM.adapters.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.config.Configurer;
import org.axonframework.config.ConfigurerModule;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventhandling.deadletter.MongoSequencedDeadLetterQueue;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.extensions.mongo.springboot.AxonMongoProperties;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public void configure(Configurer configurer, MongoClient client) {
        configurer.configureEmbeddedEventStore(configuration -> storageEngine(client))
                .eventProcessing(conf -> conf.registerTokenStore(config -> tokenStore(client, config.serializer())));
    }

    @Bean
    public EventStorageEngine storageEngine(MongoClient client) {
        return MongoEventStorageEngine.builder()
                .mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build())
                .build();
    }

    @Bean
    public TokenStore tokenStore(MongoClient client, Serializer serializer) {
        return MongoTokenStore.builder()
                .mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build())
                .serializer(serializer)
                .build();
    }

    @Bean
    public SnapshotTriggerDefinition mySnapshotTriggerDefinition(org.axonframework.config.Configuration configuration) {
        return new EventCountSnapshotTriggerDefinition(configuration.snapshotter(), 5);
    }

    @Bean
    public DefaultConfigurer configurer() {
        return (DefaultConfigurer) DefaultConfigurer.defaultConfiguration();
    }


}