package com.dat.miniATM;

import org.axonframework.springboot.autoconfig.AxonAutoConfiguration;
import org.axonframework.springboot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaEventStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "org.axonframework.extensions.mongo.springboot.autoconfig",exclude = {AxonAutoConfiguration.class, JpaAutoConfiguration.class, JdbcAutoConfiguration.class, JpaEventStoreAutoConfiguration.class} )
public class MiniAtmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniAtmApplication.class, args);
    }

}
