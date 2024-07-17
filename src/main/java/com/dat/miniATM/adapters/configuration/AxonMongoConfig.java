package com.dat.miniATM.adapters.configuration;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import org.axonframework.config.Configurer;
import org.axonframework.config.DefaultConfigurer;

import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.documentperevent.DocumentPerEventStorageStrategy;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.MongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonMongoConfig {

    @Bean
    public String hello() {
        return "hello";
    }



    public MongoClient mongoClient() {
        return MongoClient
                .create("mongodb://root:rootpassword@localhost:27017");
    }

    @Bean
    public MongoTemplate axonMongoTemplate() {
        new DefaultMongoTemplate(mongoClient());
        return DefaultMongoTemplate.builder()
                .mongoDatabase(mongoClient(), "axon")
                .build();
    }


    @Bean
    public EventStorageEngine storageEngine(MongoClient client) {
        return MongoEventStorageEngine.builder()
                .mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build())
                .build();
    }


    @Bean
    public MongoEventStorageEngine eventStorageEngine(MongoTemplate axonMongoTemplate) {
        return MongoEventStorageEngine.builder()
                .mongoTemplate(axonMongoTemplate)
                .storageStrategy(new DocumentPerEventStorageStrategy())
                .build();
    }

    @Bean
    public TokenStore tokenStore(MongoTemplate axonMongoTemplate) {
        return MongoTokenStore.builder()
                .mongoTemplate(axonMongoTemplate).build();
    }

    @Bean
    public EmbeddedEventStore embeddedEventStore(MongoEventStorageEngine mongoEventStorageEngine) {
        return new EmbeddedEventStore(mongoEventStorageEngine);
    }

    @Bean
    public Configurer configurer(EmbeddedEventStore embeddedEventStore, TokenStore tokenStore) {
        Configurer configurer = DefaultConfigurer.defaultConfiguration();

        configurer.configureEventStore(c -> embeddedEventStore);
        configurer.registerComponent(TokenStore.class, c -> tokenStore);

        configurer.buildConfiguration();
        configurer.start();
        return configurer;

    }

}