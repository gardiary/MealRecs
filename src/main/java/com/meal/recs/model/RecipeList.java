package com.meal.recs.model;

import com.meal.recs.repo.RecipeRepo;

import java.util.HashMap;
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

  public void removeRecipe(Long recipeId) {
    recipeMap.remove(recipeId);
  }

  public  Map<Long, Ingredient> getTotalIngredients() {
    Map<Long, Ingredient> ingredientTotals = new HashMap<>();
    for(Map.Entry<Long, Recipe> entry : recipeMap.entrySet()) {
      Recipe recipe = entry.getValue();

      for(Map.Entry<Long, Ingredient> ingredientEntry : recipe.getIngredients().entrySet()) {
        Ingredient ingredient = ingredientEntry.getValue();

        if(ingredient.isSelected()) {
          Ingredient sumIngredient = ingredientTotals.get(ingredient.getItem().getId());

          if (sumIngredient != null) {
            sumIngredient.setAmount(sumIngredient.getAmount() + ingredient.getAmount());
          } else {
            sumIngredient = new Ingredient(ingredient.getItem(), ingredient.getAmount());

            ingredientTotals.put(ingredient.getItem().getId(), sumIngredient);
          }
        }
      }
    }

    // calculate package here
    for(Map.Entry<Long, Ingredient> entry : ingredientTotals.entrySet()) {
      Ingredient ingredient = entry.getValue();

      IngredientPackage ingredientPackage = RecipeRepo.getIngredientPackage(ingredient.getItem().getId());

      Double packageCount = Math.ceil(ingredient.getAmount() / ingredientPackage.getItemPackage());

      ingredient.setPackageCountText(packageCount.intValue() + " " + ingredientPackage.getUnit());
    }

    return ingredientTotals;
  }
}
