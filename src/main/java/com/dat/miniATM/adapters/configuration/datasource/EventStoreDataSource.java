//package com.dat.miniATM.adapters.configuration.datasource;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class EventStoreDataSource {
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.eventStore")
//    public DataSourceProperties eventStoreProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//
//    public DataSource eventStoreDataSource(@Qualifier("eventStoreProperties") DataSourceProperties properties) {
//        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }
//}
