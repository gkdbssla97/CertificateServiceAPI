package com.example.miniProj.webconfig;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(final CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://localhost:8080/join?#",
                        "https://api-stg.passauth.co.kr/v1/certification/notice",
                        "https://api-stg.passauth.co.kr/v1/certification/result")
                .allowedMethods("GET", "POST")
                .allowCredentials(true);
    }
}
