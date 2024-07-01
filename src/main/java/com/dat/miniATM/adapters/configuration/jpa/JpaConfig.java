//package com.dat.miniATM.adapters.configuration.jpa;
//
//import com.dat.miniATM.adapters.configuration.datasource.EventStoreDataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.Objects;
//
//@Configuration
//@EnableJpaRepositories(
//        basePackages = "com.dat.miniATM.adapters.out.persistence.repositories",
//        entityManagerFactoryRef = "eventStoreEntityManagerFactory",
//        transactionManagerRef = "eventStoreTransactionManager"
//)
//@Import(EventStoreDataSource.class)
//public class JpaConfig {
//
//    @Bean(name = "eventStoreEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean eventStoreEntityManagerFactory(
//            @Qualifier("eventStoreDataSource") DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com.dat.miniATM.adapters.out.persistence.entities");
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return em;
//    }
//
//    @Bean(name = "eventStoreTransactionManager")
//    public PlatformTransactionManager eventStoreTransactionManager(
//            @Qualifier("eventStoreEntityManagerFactory") LocalContainerEntityManagerFactoryBean eventStoreEntityManagerFactory) {
//        return new JpaTransactionManager(Objects.requireNonNull(eventStoreEntityManagerFactory.getObject()));
//    }
//}
