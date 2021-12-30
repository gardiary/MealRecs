package com.meal.recs.service;

import com.meal.recs.data.entity.IngredientItemEntity;
import com.meal.recs.data.repository.IngredientItemRepository;
import com.meal.recs.model.IngredientItem;
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
 * Date: 27/12/21, 15.46
 */
@Service
public class IngredientItemService {
    public static final Sort NAME_ASC = Sort.by(Sort.Order.asc("name"));

    @Autowired
    private IngredientItemRepository repository;

    public IngredientItemEntity get(Long id) {
        return repository.getById(id);
    }

    public IngredientItemEntity get(Source source, String extId) {
        return repository.getBySourceAndExtId(source, extId);
    }

    public void save(IngredientItemEntity entity) {
        repository.save(entity);
    }

    public void save(String name, IngredientUnit unit, String image, Source source, String extId) {
        IngredientItemEntity entity = new IngredientItemEntity();
        entity.setName(name);
        entity.setUnit(unit);
        entity.setImage(image);
        entity.setSource(source);
        entity.setExtId(extId);
        repository.save(entity);
    }

    public List<IngredientItemEntity> findAll() {
        return repository.findAll(NAME_ASC);
    }

    public List<IngredientItemEntity> findAll(Integer page, Integer size) {
        if(page < 1) {
            page = 1;
        }

        Pageable pageable = PageRequest.of(page - 1, size, NAME_ASC);
        return repository.findAll(pageable).getContent();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Long countAll() {
        return repository.count();
    }
}
