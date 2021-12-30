package com.meal.recs.data.repository;

import com.meal.recs.data.entity.IngredientItemEntity;
import com.meal.recs.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * User: gardiary
 * Date: 27/12/21, 14.12
 */
@Repository
public interface IngredientItemRepository extends JpaRepository<IngredientItemEntity, Long>, JpaSpecificationExecutor<IngredientItemEntity> {
    IngredientItemEntity getBySourceAndExtId(Source source, String extId);

    //Long countByStore(Store store);
}
