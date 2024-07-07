package com.dat.miniATM.adapters.configuration.config;

import com.thoughtworks.xstream.XStream;
import jakarta.persistence.EntityManagerFactory;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import org.springframework.context.annotation.Primary;

@Configuration
public class EventStoreConfig {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TransactionManager transactionManager;

//    @Bean
//    public EventStorageEngine eventStorageEngine() throws SQLException {
//        return JpaEventStorageEngine.builder()
//                .entityManagerProvider(() -> entityManagerFactory.createEntityManager())
//                .dataSource(dataSource)
//                .transactionManager(transactionManager)
//                .build();
//    }



    @Bean
    @Primary
    public XStream xStream() {
        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[] {
                "com.dat.miniATM.**",
                "org.axonframework.**"
        });
        return xStream;
    }

    @Bean
    @Primary
    public Serializer serializer(XStream xStream) {
        return XStreamSerializer.builder()
                .xStream(xStream)
                .build();
    }
}