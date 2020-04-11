package com.kt.wms.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 16:09
 * @description 加载mapper
 */
@Configuration
@MapperScan("com.kt.wms.mapper")
@EnableTransactionManagement
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
