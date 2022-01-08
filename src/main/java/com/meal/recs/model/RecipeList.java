package com.meal.recs.model;

import com.meal.recs.repo.RecipeRepo;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gardiary on 28/01/19.
 */
public class RecipeList {
  private final Logger LOGGER = LoggerFactory.getLogger(getClass());
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

        for (Map.Entry<Long, Ingredient> ingredientEntry : recipe.getIngredientsMap().entrySet()) {
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

        IngredientPackage ingredientPackage = RecipeRepo.getStaticIngredientPackage(ingredient.getItem().getId());

        Double packageCount = Math.ceil(ingredient.getAmount() / ingredientPackage.getItemAmount());

        ingredient.setPackageCount(packageCount.intValue());
        ingredient.setPackageCountText(packageCount.intValue() + " " +
            determineUnit(ingredientPackage, packageCount.intValue(), true) + ingredientPackage.getItem().getName());
        ingredient.setPackageCountText2(packageCount.intValue() + " " +
            determineUnit(ingredientPackage, packageCount.intValue(), false) + ingredientPackage.getItem().getName());
      }
    }

    return ingredientTotals;
  }

  public Map<Long, Ingredient> getTotalIngredientsNew() {
    Map<Long, Ingredient> ingredientTotals = new HashMap<>();

    if (recipeMap.size() > 0) {
      for (Map.Entry<Long, Recipe> entry : recipeMap.entrySet()) {
        Recipe recipe = entry.getValue();

        for (Map.Entry<Long, Ingredient> ingredientEntry : recipe.getIngredientsMap().entrySet()) {
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

        if(ingredientPackage != null) {
          ingredient.getItem().setPackageExists(true);
          Double packageCount = Math.ceil(ingredient.getAmount() / ingredientPackage.getItemAmount());

          ingredient.setPackageCount(packageCount.intValue());
          ingredient.setPackageCountText(packageCount.intValue() + " " +
                  determineUnit(ingredientPackage, packageCount.intValue(), true) + ingredientPackage.getItem().getName());
          ingredient.setPackageCountText2(packageCount.intValue() + " " +
                  determineUnit(ingredientPackage, packageCount.intValue(), false) + ingredientPackage.getItem().getName());
        } else {
          ingredient.setPackageCountText(ingredient.getAmountAsString() + " " +
                  determineUnit(ingredient.getItem().getUnit(), ingredient.getAmount(), true) + ingredient.getItem().getName());
          ingredient.setPackageCountText2(ingredient.getAmountAsString() + " " +
                  determineUnit(ingredient.getItem().getUnit(), ingredient.getAmount(), false) + ingredient.getItem().getName());
        }
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

  public List<String> getTotalIngredientsPackageCountNew() {
    Map<Long, Ingredient> totalIngredients = getTotalIngredientsNew();
    List<String> ingredients = new ArrayList<>();

    if (totalIngredients != null && !totalIngredients.isEmpty()) {
      for (Map.Entry<Long, Ingredient> entry : totalIngredients.entrySet()) {
        Ingredient ingredient = entry.getValue();
        if(ObjectUtils.isNotEmpty(ingredient.getPackageCountText2())) {
          ingredients.add(ingredient.getPackageCountText2());
        }
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
    //if (!ingredientPackage.getUnit().equals("")) {
    if (ingredientPackage.getUnit() != null) {
      if (packageCount > 1) {
        if (ingredientPackage.getUnit() == PackageUnit.BOX) {
          return ingredientPackage.getUnit().getTitle() + (withOf ? "es of " : "es ");
        } else {
          return ingredientPackage.getUnit().getTitle() + (withOf ? "s of " : "s ");
        }
      } else {
        return ingredientPackage.getUnit().getTitle() + (withOf ? " of " : " ");
      }
    } else {
      return "";
    }
  }

  private String determineUnit(IngredientUnit ingredientUnit, Double amount, boolean withOf) {
    if (ingredientUnit != null) {
      if (amount > 1) {
        if (ingredientUnit == IngredientUnit.BOX) {
          return ingredientUnit.name().toLowerCase() + (withOf ? "es of " : "es ");
        } else {
          return ingredientUnit.name().toLowerCase() + (withOf ? "s of " : "s ");
        }
      } else {
        return ingredientUnit.name().toLowerCase() + (withOf ? " of " : " ");
      }
    } else {
      return "";
    }
  }
}
