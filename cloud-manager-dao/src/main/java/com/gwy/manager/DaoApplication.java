package com.gwy.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.gwy.manager.mapper")
@EnableCircuitBreaker //添加对熔断的支持
public class DaoApplication {
    public static void main(String[] args){
        SpringApplication.run(com.gwy.manager.DaoApplication.class,args);
    }
}
