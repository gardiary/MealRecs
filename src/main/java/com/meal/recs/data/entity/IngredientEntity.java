package com.meal.recs.data.entity;

import com.meal.recs.model.IngredientItem;
import com.meal.recs.model.IngredientUnit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * User: gardiary
 * Date: 03/01/22, 07.38
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="mr_ingredient")
public class IngredientEntity extends BaseEntity {
    //@ManyToOne
    //@JoinColumn(name = "recipe_id")
    //private RecipeEntity recipe;

    @Column(name = "recipe_id", nullable = false)
    private Long recipeId;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private IngredientItemEntity item;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "unit", length = 20)
    @Enumerated(value = EnumType.STRING)
    private IngredientUnit unit;
}
