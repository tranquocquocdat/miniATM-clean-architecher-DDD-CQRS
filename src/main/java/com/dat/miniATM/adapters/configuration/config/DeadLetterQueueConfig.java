package com.dat.miniATM.adapters.configuration.config;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.config.ConfigurerModule;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.deadletter.jpa.JpaSequencedDeadLetterQueue;
import org.axonframework.messaging.deadletter.Decisions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DeadLetterQueueConfig {

    @Bean
    public ConfigurerModule deadLetterQueueConfigurerModule() {
        return configurer -> configurer.eventProcessing().registerDeadLetterQueue(
                "customer",
                config -> JpaSequencedDeadLetterQueue.builder()
                        .processingGroup("customer")
                        .entityManagerProvider(config.getComponent(EntityManagerProvider.class))
                        .transactionManager(config.getComponent(TransactionManager.class))
                        .serializer(config.serializer())
                        .build()
        );
    }

    @Bean
    public ConfigurerModule enqueuePolicyConfigurerModule() {
        return configurer -> configurer.eventProcessing()
                .registerDefaultDeadLetterPolicy(
                        config -> new RetryConstrainedEnqueuePolicy()
                );
    }
}