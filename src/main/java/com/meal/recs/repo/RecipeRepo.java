package com.meal.recs.repo;

import com.meal.recs.model.Ingredient;
import com.meal.recs.model.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gardiary on 25/01/19.
 */
public class RecipeRepo {
  private static List<Recipe> recipes;
  private static Map<Long, Recipe> recipeMap;
  private static Map<Long, List<Long>> recommendationRecipes;

  static {
    recipes = new ArrayList<>();
    recipeMap = new HashMap<>();
    recommendationRecipes = new HashMap<>();

    Recipe recipe = new Recipe(1L, "Baked Ziti", "recipe-1.png");
    recipe.addIngredient(new Ingredient(1L, "Pasta", "1"));
    recipe.addIngredient(new Ingredient(2L, "Mozzerella", "0.75"));
    recipe.addIngredient(new Ingredient(3L, "Ground Beef", "1.25"));
    recipe.addIngredient(new Ingredient(4L, "Red Sauce", "1.5"));
    recipe.addIngredient(new Ingredient(5L, "Olive Oil", "0.1"));
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(2L, 4L, 6L));

    recipe = new Recipe(2L, "Meatballs", "recipe-2.png");
    recipe.addIngredient(new Ingredient(6L, "Ground Beef", "0.75"));
    recipe.addIngredient(new Ingredient(7L, "Mozzerella", "0.25"));
    recipe.addIngredient(new Ingredient(8L, "Red Sauce", "0.5"));
    recipe.addIngredient(new Ingredient(9L, "Bread Crumbs", "0.33"));
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(3L, 5L, 1L));

    recipe = new Recipe(3L, "Tacos", "recipe-3.png");
    recipe.addIngredient(new Ingredient(10L, "Ground Beef", "1.25"));
    recipe.addIngredient(new Ingredient(11L, "Mozzerella", "0.75"));
    recipe.addIngredient(new Ingredient(12L, "Tomatoes", "2"));
    recipe.addIngredient(new Ingredient(13L, "Lettuce", "0.33"));
    recipe.addIngredient(new Ingredient(14L, "Mushrooms", "0.33"));
    recipe.addIngredient(new Ingredient(15L, "Tortillas", "0.1"));
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(6L, 2L, 5L));

    recipe = new Recipe(4L, "Chicken Parm", "recipe-4.png");
    recipe.addIngredient(new Ingredient(16L, "Chicken", "1"));
    recipe.addIngredient(new Ingredient(17L, "Bread Crumbs", "0.67"));
    recipe.addIngredient(new Ingredient(18L, "Red Sauce", "0.5"));
    recipe.addIngredient(new Ingredient(19L, "Mozzerella", "0.25"));
    recipe.addIngredient(new Ingredient(20L, "Olive Oil", "0.2"));
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(2L, 6L, 3L));

    recipe = new Recipe(5L, "Chicken Enchilada", "recipe-5.png");
    recipe.addIngredient(new Ingredient(21L, "Chicken", "0.75"));
    recipe.addIngredient(new Ingredient(22L, "Mushrooms", "0.33"));
    recipe.addIngredient(new Ingredient(23L, "Tortillas", "1"));
    recipe.addIngredient(new Ingredient(24L, "Mozzerella", "0.25"));
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(3L, 4L, 1L));

    recipe = new Recipe(6L, "Chicken Marsala", "recipe-6.png");
    recipe.addIngredient(new Ingredient(25L, "Chicken", "1.25"));
    recipe.addIngredient(new Ingredient(26L, "Mushrooms", "0.34"));
    recipe.addIngredient(new Ingredient(27L, "Olive Oil", "0.2"));
    recipe.addIngredient(new Ingredient(28L, "Chicken Broth", "0.5"));
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(5L, 2L, 4L));
  }

  public static List<Recipe> getRecipes() {
    return recipes;
  }

  public static Recipe getRecipe(Long id) {
    return recipeMap.get(id);
  }

  public static List<List<Recipe>> getMultiLinesRecipes(int column) {
    List<List<Recipe>> multiLinesRecipes = new ArrayList<>();

    int row = 1;
    List<Recipe> oneRowRecipes = new ArrayList<>();
    for(int i = 0; i < recipes.size(); i++) {
      Recipe recipe = recipes.get(i);

      oneRowRecipes.add(recipe);

      if(i == recipes.size() -1) {
        multiLinesRecipes.add(oneRowRecipes);
      } else {
        if ((i + 1) % column == 0) {
          multiLinesRecipes.add(oneRowRecipes);

          oneRowRecipes = new ArrayList<>();
        }
      }

    }

    return multiLinesRecipes;
  }

  public static List<Recipe> getRecommendationRecipes(Long id) {
    List<Recipe> recRecipes = new ArrayList<>();

    for(Long recId : recommendationRecipes.get(id)) {
      recRecipes.add(recipeMap.get(recId));
    }

    return recRecipes;
  }
}
