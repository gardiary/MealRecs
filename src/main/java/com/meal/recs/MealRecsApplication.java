package com.meal.recs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by gardiary on 22/01/19.
 */
@SpringBootApplication
public class MealRecsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MealRecsApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/recipe/list")
                        .allowedOrigins("http://localhost:8000",
                                "https://mealrecs.herokuapp.com",
                                "https://cdn.whisk.com");
            }
        };
    }
}
