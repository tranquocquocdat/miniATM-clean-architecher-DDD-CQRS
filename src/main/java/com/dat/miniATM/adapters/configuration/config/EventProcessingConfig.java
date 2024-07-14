//package com.dat.miniATM.adapters.configuration.config;
//
//import org.axonframework.common.jpa.EntityManagerProvider;
//import org.axonframework.common.transaction.TransactionManager;
//import org.axonframework.config.EventProcessingConfigurer;
//import org.axonframework.eventhandling.deadletter.jpa.JpaSequencedDeadLetterQueue;
//import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//public class EventProcessingConfig {
//
//    @Autowired
//    public void configure(EventProcessingConfigurer configurer) {
//        configurer.registerTrackingEventProcessor("myTrackingProcessor", Configuration::eventBus);
//
//        configurer.registerDeadLetterQueue("myProcessingGroup", config -> JpaSequencedDeadLetterQueue.builder()
//                .processingGroup("myProcessingGroup")
//                .entityManagerProvider(config.getComponent(EntityManagerProvider.class))
//                .transactionManager(config.getComponent(TransactionManager.class))
//                .serializer(config.serializer())
//                .build());
//
//        configurer.registerDefaultDeadLetterPolicy(config -> new RetryConstrainedEnqueuePolicy());
//
//        configurer.registerDefaultErrorHandler(cionfig -> (error, event) -> {
//            // Xử lý lỗi
//        });
//
//        configurer.registerTransactionManager("myProcessor", config -> new SpringTransactionManager(transactionManager()));
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new JpaTransactionManager();
//    }
//}