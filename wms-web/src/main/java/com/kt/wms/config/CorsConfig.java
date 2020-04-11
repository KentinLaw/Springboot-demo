package com.kt.wms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 16:09
 * @description CorsConfig
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域访问的路径
        registry.addMapping("/**")
                // 允许跨域访问的源
                .allowedOrigins("http://localhost:8080")
                // 允许请求方法
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                // 预检间隔时间
                .maxAge(168000)
                // 允许头部设置
                .allowedHeaders("*")
                .allowCredentials(true)
        ;
    }
}