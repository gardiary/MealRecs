package com.meal.recs.spoonacular;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * User: gardiary
 * Date: 01/01/22, 07.13
 */
@Data
@NoArgsConstructor
@ToString
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeInstructionStep {
    private Integer number;
    private String step;
    private List<IngredientItem> ingredients;
}
