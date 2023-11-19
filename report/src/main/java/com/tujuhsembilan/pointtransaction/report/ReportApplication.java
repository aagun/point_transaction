package com.tujuhsembilan.pointtransaction.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EntityScan(basePackages = "com.tujuhsembilan.pointtransaction.clients.model")
@EnableFeignClients(basePackages = "com.tujuhsembilan.pointtransaction.clients")
@PropertySources({@PropertySource("classpath:clients-${spring.profiles.active}.properties")})
public class ReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportApplication.class, args);
    }
}
