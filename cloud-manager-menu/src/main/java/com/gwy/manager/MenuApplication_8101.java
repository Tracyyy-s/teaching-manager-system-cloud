package com.gwy.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.gwy.manager.mapper")
public class MenuApplication_8101 {
    public static void main(String[] args){
        SpringApplication.run(com.gwy.manager.MenuApplication_8101.class,args);
    }
}
