package com.tujuhsembilan.pointtransaction.point;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EntityScan(basePackages = {
        "com.tujuhsembilan.pointtransaction.clients.model",
        "com.tujuhsembilan.pointtransaction.point.model"
})
@EnableFeignClients(basePackages = "com.tujuhsembilan.pointtransaction.clients")
@PropertySources({@PropertySource("classpath:clients-${spring.profiles.active}.properties")})
public class PointApplication {

    public static void main(String[] args) {
        SpringApplication.run(PointApplication.class, args);
    }
}
