package com.meal.recs.data.entity;

import com.meal.recs.model.Ingredient;
import com.meal.recs.model.Source;
import com.meal.recs.model.Time;
import com.meal.recs.model.TimeUnit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: gardiary
 * Date: 27/12/21, 14.04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="mr_recipe")
public class RecipeEntity extends BaseEntity {
    @Column(name = "name", length = 160, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "prep_time")
    private Integer prepTime;

    @Column(name = "prep_time_unit")
    @Enumerated(value = EnumType.STRING)
    private TimeUnit prepTimeUnit;

    @Column(name = "cook_time")
    private Integer cookTime;

    @Column(name = "cook_time_unit")
    @Enumerated(value = EnumType.STRING)
    private TimeUnit cookTimeUnit;

    @Column(name = "servings")
    private Integer servings;

    //private Map<Long, Ingredient> ingredients = new HashMap<>();
    //private List<String> directions;
    //private List<Ingredient> neededIngredients = new ArrayList<>();

    @Column(name = "image")
    private String image;

    @Column(name = "source", length = 20)
    @Enumerated(value = EnumType.STRING)
    private Source source;

    @Column(name = "source_url")
    private String sourceUrl;

    @Column(name = "ext_id", length = 32)
    private String extId;

    @OneToMany(targetEntity = IngredientEntity.class, mappedBy = "recipeId")
    @Fetch(FetchMode.SELECT)
    //@OrderBy("createdAt")
    private List<IngredientEntity> ingredients;

    @Column(name = "directions", columnDefinition = "text")
    private String directions;
}
