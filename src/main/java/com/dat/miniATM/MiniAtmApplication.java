package com.dat.miniATM;

import com.dat.miniATM.adapters.configuration.AxonMongoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication(scanBasePackages = "org.axonframework.extensions.mongo.springboot.autoconfig",exclude = {AxonAutoConfiguration.class, JpaAutoConfiguration.class, JdbcAutoConfiguration.class, JpaEventStoreAutoConfiguration.class} )
@SpringBootApplication(scanBasePackageClasses = {AxonMongoConfig.class})
public class MiniAtmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniAtmApplication.class, args);
    }

}
