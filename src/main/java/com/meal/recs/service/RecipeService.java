package com.meal.recs.service;

import com.meal.recs.data.entity.RecipeEntity;
import com.meal.recs.data.repository.RecipeRepository;
import com.meal.recs.model.IngredientUnit;
import com.meal.recs.model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: gardiary
 * Date: 03/01/22, 10.27
 */
@Service
public class RecipeService extends BaseService {
    @Autowired
    private RecipeRepository repository;

    public RecipeEntity get(Long id) {
        return repository.getById(id);
    }

    public RecipeEntity get(Source source, String extId) {
        return repository.getBySourceAndExtId(source, extId);
    }

    public void save(RecipeEntity entity) {
        repository.save(entity);
    }

    public List<RecipeEntity> findAll() {
        return repository.findAll(NAME_ASC);
    }

    public List<RecipeEntity> findAll(Integer page, Integer size) {
        if(page < 1) {
            page = 1;
        }

        Pageable pageable = PageRequest.of(page - 1, size, NAME_ASC);
        return repository.findAll(pageable).getContent();
    }

    public List<RecipeEntity> findAll(Integer page, Integer size, Sort sort) {
        if(page < 1) {
            page = 1;
        }

        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return repository.findAll(pageable).getContent();
    }

    public List<RecipeEntity> findAll(Integer page, Integer size, List<Long> excludeIds) {
        if(page < 1) {
            page = 1;
        }

        Pageable pageable = PageRequest.of(page - 1, size);
        return repository.findAllByIdNotIn(excludeIds, pageable);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(RecipeEntity entity) {
        repository.delete(entity);
    }

    public Long countAll() {
        return repository.count();
    }

}
