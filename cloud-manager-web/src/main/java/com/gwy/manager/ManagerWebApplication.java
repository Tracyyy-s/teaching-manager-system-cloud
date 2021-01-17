package com.gwy.manager;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Tracy
 * @date 2021/1/16 11:05
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableCaching
@EnableRabbit
@EnableTransactionManagement(proxyTargetClass = true)
@EnableEurekaClient
@EnableFeignClients
public class ManagerWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerWebApplication.class, args);
    }
}
