package com.dat.miniATM;

import org.axonframework.springboot.autoconfig.AxonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude = AxonAutoConfiguration.class)
@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.dat.miniATM.adapters.out.persistence.repositories.jpql")
public class MiniAtmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniAtmApplication.class, args);
    }

}
