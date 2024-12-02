package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 모든 경로에 대해 CORS 허용
                        .allowedOrigins(
                                "http://localhost:3000", // 로컬 개발 서버
                                "https://hancheolKim.github.io" // 배포된 리액트 앱 URL
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용되는 HTTP 메서드
                        .allowCredentials(true); // 인증 정보 허용 (쿠키 등)
            }
        };
    }
}