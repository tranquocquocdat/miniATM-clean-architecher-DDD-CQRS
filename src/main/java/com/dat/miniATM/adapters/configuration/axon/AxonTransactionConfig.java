//package com.dat.miniATM.adapters.configuration.axon;
//
//import jakarta.transaction.TransactionManager;
//import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//
//@Configuration
//public class AxonTransactionConfig {
//
//    @Bean
//    public TransactionManager axonTransactionManager(PlatformTransactionManager platformTransactionManager) {
//        return new SpringTransactionManager(platformTransactionManager);
//    }
//}