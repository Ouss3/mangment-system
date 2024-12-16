package com.ouss.mangmentsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Project Name: MangmentSystem
 * File Name: CorsConfig
 * Created by: DELL
 * Created on: 12/15/2024
 * Description:
 * <p>
 * CorsConfig is a part of the MangmentSystem project.
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
               registry.addMapping("/**")
                       .allowedOrigins("*")
                       .allowedMethods("GET","POST","PUT","DELETE");

            }
        };
    }
}
