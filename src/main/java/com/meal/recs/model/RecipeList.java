package com.meal.recs.model;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gardiary on 28/01/19.
 */
public class RecipeList {
  private Map<Long, Recipe> recipeMap = new HashMap<>();

  public Map<Long, Recipe> getRecipeMap() {
    return recipeMap;
  }

  public void setRecipeMap(Map<Long, Recipe> recipeMap) {
    this.recipeMap = recipeMap;
  }

  public void addRecipe(Recipe recipe) {
    if(recipeMap == null) {
      recipeMap = new HashMap<>();
    }

    recipeMap.put(recipe.getId(), recipe);
  }
}
