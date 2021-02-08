package com.ooredoo.franchise;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@EnableAutoConfiguration
@SpringBootApplication
public class FranchiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(FranchiseApplication.class, args);
    }

}
