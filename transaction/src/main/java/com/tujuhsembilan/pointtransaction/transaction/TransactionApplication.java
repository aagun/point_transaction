package com.tujuhsembilan.pointtransaction.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EntityScan(basePackages = "com.tujuhsembilan.pointtransaction.clients.model")
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class TransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }
}


