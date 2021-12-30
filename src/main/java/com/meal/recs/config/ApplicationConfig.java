package com.meal.recs.config;

import com.meal.recs.navigator.PageNavigator;
import com.meal.recs.navigator.Bootstrap4Navigator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * User: gardiary
 * Date: 28/12/21, 22.31
 */
@Configuration
@EnableJpaAuditing
public class ApplicationConfig {
    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }

    @Bean
    public PageNavigator pageNavigator() {
        return new Bootstrap4Navigator();
    }
}
