package com.dat.miniATM;

import org.axonframework.commandhandling.distributed.DistributedCommandBus;
import org.axonframework.springboot.autoconfig.AxonAutoConfiguration;
import org.axonframework.springboot.autoconfig.AxonServerAutoConfiguration;
import org.axonframework.eventhandling.tokenstore;
import org.axonframework.springboot.autoconfig.JpaEventStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude = {AxonAutoConfiguration.class, JpaEventStoreAutoConfiguration.class, JdbcAutoConfiguration.class, AxonServerAutoConfiguration.class,
//})
@SpringBootApplication("org.axonframework.eventhandling.tokenstore.jpa")
//@EnableJpaRepositories(basePackages = "com.dat.miniATM.adapters.out.persistence.repositories.jpql")
public class MiniAtmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniAtmApplication.class, args);
    }

}
