package com.gwy.manager.config.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tracy
 * @date 2021/1/16 11:24
 */
@Configuration
@MapperScan("com.gwy.manager.mapper")
public class MapperConfiguration {
}
