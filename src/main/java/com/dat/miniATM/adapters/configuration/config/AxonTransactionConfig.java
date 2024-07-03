package com.dat.miniATM.adapters.configuration.config;


import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AxonTransactionConfig {

    @Bean
    @Primary
    public TransactionManager axonTransactionManagerCustom(PlatformTransactionManager platformTransactionManager) {
        return (TransactionManager) new SpringTransactionManager(platformTransactionManager);
    }
}