package com.meal.recs.service;

import com.meal.recs.data.entity.IngredientEntity;
import com.meal.recs.data.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: gardiary
 * Date: 03/01/22, 10.28
 */
@Service
public class IngredientService extends BaseService {

    @Autowired
    private IngredientRepository repository;

    public IngredientEntity get(Long id) {
        return repository.getById(id);
    }

    public void save(IngredientEntity entity) {
        repository.save(entity);
    }

    public void saveAll(List<IngredientEntity> entities) {
        repository.saveAll(entities);
    }

    public List<IngredientEntity> findAll() {
        return repository.findAll(ID_ASC);
    }

    public List<IngredientEntity> findAll(Long recipeId) {
        return repository.findAllByRecipeId(recipeId, ID_ASC);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll(List<IngredientEntity> entities) {
        repository.deleteAll(entities);
    }

    public Long countAll() {
        return repository.count();
    }
}
