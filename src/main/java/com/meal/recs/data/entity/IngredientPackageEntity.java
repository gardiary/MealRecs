package com.meal.recs.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: gardiary
 * Date: 27/12/21, 14.06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
/*@Entity
@Table(name="mr_ingredient_package")*/
public class IngredientPackageEntity extends BaseEntity {

}
