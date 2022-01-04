package com.meal.recs.data.repository;

import com.meal.recs.data.entity.RecipeEntity;
import com.meal.recs.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * User: gardiary
 * Date: 27/12/21, 14.11
 */
@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long>, JpaSpecificationExecutor<RecipeEntity> {
    RecipeEntity getBySourceAndExtId(Source source, String extId);
}
