package com.github.fttroy.reminder.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig {

    @Bean
    public WebMvcConfigurer swaggerCorsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Permetti CORS solo sulle rotte di Swagger/OpenAPI
                registry.addMapping("/v3/api-docs/**")
                        .allowedOrigins("*") // o specifica i domini ammessi
                        .allowedMethods("GET", "POST", "OPTIONS")
                        .allowedHeaders("*");

                registry.addMapping("/swagger-ui/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
