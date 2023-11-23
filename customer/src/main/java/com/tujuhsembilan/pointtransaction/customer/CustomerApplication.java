package com.tujuhsembilan.pointtransaction.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EntityScan(basePackages = "com.tujuhsembilan.pointtransaction.clients.model")
@EnableFeignClients(basePackages = "com.tujuhsembilan.pointtransaction.clients")
@PropertySources({@PropertySource("classpath:clients-${spring.profiles.active}.properties")})
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
