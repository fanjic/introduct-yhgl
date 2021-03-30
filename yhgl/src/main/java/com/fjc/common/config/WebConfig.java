package com.fjc.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    /** 跨域请求配置
     * 1.访问路径
     * 2.请求路径
     * 3.方法
     * 4.允许携带token
     * 5.设置最大响应时间
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080","http://localhost:8081")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3000);
    }
}
