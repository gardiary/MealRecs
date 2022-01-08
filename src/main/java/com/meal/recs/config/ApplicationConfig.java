package com.meal.recs.config;

import com.meal.recs.data.entity.IngredientPackageEntity;
import com.meal.recs.model.IngredientPackage;
import com.meal.recs.navigator.PageNavigator;
import com.meal.recs.navigator.Bootstrap4Navigator;
import com.meal.recs.repo.RecipeRepo;
import com.meal.recs.service.IngredientPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: gardiary
 * Date: 28/12/21, 22.31
 */
@Configuration
@EnableJpaAuditing
public class ApplicationConfig {
    @Autowired
    private IngredientPackageService ingredientPackageService;

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

    @PostConstruct
    public void loadData() {
        List<IngredientPackageEntity> ingredientPackageEntities = ingredientPackageService.findAll();
        for(IngredientPackageEntity ingredientPackageEntity : ingredientPackageEntities) {
            RecipeRepo.addIngredientPackage(new IngredientPackage(ingredientPackageEntity));
        }
    }
}
