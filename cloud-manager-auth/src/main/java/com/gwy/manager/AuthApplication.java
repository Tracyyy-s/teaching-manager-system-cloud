package com.gwy.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Tracy
 * @date 2021/1/16 11:11
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableRabbit
@MapperScan("com.gwy.manager.mapper")
@EnableEurekaClient
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
