package com.meal.recs.data.repository;

import com.meal.recs.data.entity.IngredientItemEntity;
import com.meal.recs.data.entity.IngredientPackageEntity;
import com.meal.recs.model.PackageUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * User: gardiary
 * Date: 27/12/21, 14.13
 */
@Repository
public interface IngredientPackageRepository extends JpaRepository<IngredientPackageEntity, Long>, JpaSpecificationExecutor<IngredientPackageEntity> {
    IngredientPackageEntity getByItem(IngredientItemEntity item);
    IngredientPackageEntity getByUnitAndItem(PackageUnit unit, IngredientItemEntity item);
}
