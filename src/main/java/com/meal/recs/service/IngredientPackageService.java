package com.meal.recs.service;

import com.meal.recs.data.entity.IngredientItemEntity;
import com.meal.recs.data.entity.IngredientPackageEntity;
import com.meal.recs.data.repository.IngredientPackageRepository;
import com.meal.recs.model.PackageUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: gardiary
 * Date: 06/01/22, 08.56
 */
@Service
public class IngredientPackageService extends BaseService {
    @Autowired
    private IngredientPackageRepository repository;

    public IngredientPackageEntity get(Long id) {
        return repository.getById(id);
    }

    public IngredientPackageEntity get(IngredientItemEntity item) {
        return repository.getByItem(item);
    }

    public IngredientPackageEntity get(PackageUnit unit, IngredientItemEntity item) {
        return repository.getByUnitAndItem(unit, item);
    }

    public void save(IngredientPackageEntity entity) {
        repository.save(entity);
    }

    public List<IngredientPackageEntity> findAll() {
        return repository.findAll(ITEM_NAME_ASC);
    }

    public List<IngredientPackageEntity> findAll(Integer page, Integer size) {
        if(page < 1) {
            page = 1;
        }

        Pageable pageable = PageRequest.of(page - 1, size, ITEM_NAME_ASC);
        return repository.findAll(pageable).getContent();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Long countAll() {
        return repository.count();
    }
}
