package com.meal.recs.model;

import com.meal.recs.repo.RecipeRepo;

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
    if (recipeMap == null) {
      recipeMap = new HashMap<>();
    }

    recipeMap.put(recipe.getId(), recipe);
  }

  public void removeRecipe(Long recipeId) {
    recipeMap.remove(recipeId);
  }

  public Map<Long, Ingredient> getTotalIngredients() {
    Map<Long, Ingredient> ingredientTotals = new HashMap<>();

    if (recipeMap.size() > 0) {
      for (Map.Entry<Long, Recipe> entry : recipeMap.entrySet()) {
        Recipe recipe = entry.getValue();

        for (Map.Entry<Long, Ingredient> ingredientEntry : recipe.getIngredients().entrySet()) {
          Ingredient ingredient = ingredientEntry.getValue();

          if (ingredient.isSelected()) {
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
      for (Map.Entry<Long, Ingredient> entry : ingredientTotals.entrySet()) {
        Ingredient ingredient = entry.getValue();

        IngredientPackage ingredientPackage = RecipeRepo.getIngredientPackage(ingredient.getItem().getId());

        Double packageCount = Math.ceil(ingredient.getAmount() / ingredientPackage.getItemPackage());

        ingredient.setPackageCount(packageCount.intValue());
        ingredient.setPackageCountText(packageCount.intValue() + " " +
            determineUnit(ingredientPackage, packageCount.intValue(), true) + ingredientPackage.getItem().getName());
        ingredient.setPackageCountText2(packageCount.intValue() + " " +
            determineUnit(ingredientPackage, packageCount.intValue(), false) + ingredientPackage.getItem().getName());
      }
    }

    return ingredientTotals;
  }

  public String getTotalIngredientsAsJsArray() {
    Map<Long, Ingredient> totalIngredients = getTotalIngredients();

    StringBuilder sb = new StringBuilder("[");
    if (totalIngredients != null && !totalIngredients.isEmpty()) {
      int count = 0;
      for (Map.Entry<Long, Ingredient> entry : totalIngredients.entrySet()) {
        count += 1;
        Ingredient ingredient = entry.getValue();
        sb.append("\'").append(ingredient.getItem().getName()).append("\'");

        if (count < totalIngredients.size()) {
          sb.append(", ");
        }
      }
    }
    sb.append("]");

    return sb.toString();
  }

  public List<String> getTotalIngredientsNameOnly() {
    Map<Long, Ingredient> totalIngredients = getTotalIngredients();
    List<String> ingredients = new ArrayList<>();

    if (totalIngredients != null && !totalIngredients.isEmpty()) {
      for (Map.Entry<Long, Ingredient> entry : totalIngredients.entrySet()) {
        Ingredient ingredient = entry.getValue();
        ingredients.add(ingredient.getItem().getName());
      }
    }

    return ingredients;
  }

  public List<String> getTotalIngredientsPackageCount() {
    Map<Long, Ingredient> totalIngredients = getTotalIngredients();
    List<String> ingredients = new ArrayList<>();

    if (totalIngredients != null && !totalIngredients.isEmpty()) {
      for (Map.Entry<Long, Ingredient> entry : totalIngredients.entrySet()) {
        Ingredient ingredient = entry.getValue();
        ingredients.add(ingredient.getPackageCountText2());
      }
    }

    return ingredients;
  }

  public List<ShopItem> getTotalIngredientsShopItem() {
    Map<Long, Ingredient> totalIngredients = getTotalIngredients();
    List<ShopItem> ingredients = new ArrayList<>();

    if (totalIngredients != null && !totalIngredients.isEmpty()) {
      for (Map.Entry<Long, Ingredient> entry : totalIngredients.entrySet()) {
        Ingredient ingredient = entry.getValue();
        ingredients.add(new ShopItem(ingredient.getItem().getName(), ingredient.getPackageCount()));
      }
    }

    return ingredients;
  }

  private String determineUnit(IngredientPackage ingredientPackage, int packageCount, boolean withOf) {
    if (!ingredientPackage.getUnit().equals("")) {
      if (packageCount > 1) {
        if (ingredientPackage.getUnit().equalsIgnoreCase("Box")) {
          return ingredientPackage.getUnit() + (withOf ? "es of " : "es ");
        } else {
          return ingredientPackage.getUnit() + (withOf ? "s of " : "s ");
        }
      } else {
        return ingredientPackage.getUnit() + (withOf ? " of " : " ");
      }
    } else {
      return "";
    }
  }
}
