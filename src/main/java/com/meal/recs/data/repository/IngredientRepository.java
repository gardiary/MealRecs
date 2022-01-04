package com.meal.recs.data.repository;

import com.meal.recs.data.entity.IngredientEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: gardiary
 * Date: 03/01/22, 10.15
 */
@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long>, JpaSpecificationExecutor<IngredientEntity> {
    List<IngredientEntity> findAllByRecipeId(Long recipeId, Sort sort);
}
