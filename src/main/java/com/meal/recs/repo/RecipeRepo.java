package com.meal.recs.repo;

import com.meal.recs.model.Ingredient;
import com.meal.recs.model.IngredientItem;
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
  private static Map<Long, IngredientItem> ingredientItemMap;

  static {
    recipes = new ArrayList<>();
    recipeMap = new HashMap<>();
    recommendationRecipes = new HashMap<>();

    ingredientItemMap = new HashMap<>();
    ingredientItemMap.put(1L, new IngredientItem(1L, "Pasta", "pcs"));
    ingredientItemMap.put(2L, new IngredientItem(2L, "Mozzerella", "pcs"));
    ingredientItemMap.put(3L, new IngredientItem(3L, "Ground Beef", "pcs"));
    ingredientItemMap.put(4L, new IngredientItem(4L, "Red Sauce", "pcs"));
    ingredientItemMap.put(5L, new IngredientItem(5L, "Olive Oil", "pcs"));
    ingredientItemMap.put(6L, new IngredientItem(6L, "Bread Crumbs", "pcs"));
    ingredientItemMap.put(7L, new IngredientItem(7L, "Tomatoes", "pcs"));
    ingredientItemMap.put(8L, new IngredientItem(8L, "Lettuce", "pcs"));
    ingredientItemMap.put(9L, new IngredientItem(9L, "Mushrooms", "pcs"));
    ingredientItemMap.put(10L, new IngredientItem(10L, "Tortillas", "pcs"));
    ingredientItemMap.put(11L, new IngredientItem(11L, "Chicken", "pcs"));
    ingredientItemMap.put(12L, new IngredientItem(12L, "Chicken Broth", "pcs"));

    Recipe recipe = new Recipe(1L, "Baked Ziti", "recipe-1.png");
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(1L), 1D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(2L), 0.75));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(3L), 1.25));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(4L), 1.5));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(5L), 0.1));
    recipe.addDirection("Boil water and cook pasta for 10 minutes");
    recipe.addDirection("Cook meat until brown");
    recipe.addDirection("Preheat oven to 350 degrees");
    recipe.addDirection("Oil baking pan and mix sauce, cheese, pasta, and meat and cook for 20 minutes");
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(2L, 4L, 6L));

    recipe = new Recipe(2L, "Meatballs", "recipe-2.png");
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(3L), 0.75));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(2L), 0.25));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(4L), 0.5));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(6L), 0.33));
    recipe.addDirection("Mix cheese and bread crumbs in raw meat");
    recipe.addDirection("Place handful size balls of meat on a baking sheet");
    recipe.addDirection("Preheat oven to 350 degrees");
    recipe.addDirection("Bake for 30 minutes");
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(3L, 5L, 1L));

    recipe = new Recipe(3L, "Tacos", "recipe-3.png");
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(3L), 1.25));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(2L), 0.75));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(7L), 2D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(8L), 0.33));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(9L), 0.33));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(10L), 0.1));
    recipe.addDirection("Brown meat over medium heat");
    recipe.addDirection("Season beef to tasting");
    recipe.addDirection("Heat tortillas for 11 seconds before making taco");
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(6L, 2L, 5L));

    recipe = new Recipe(4L, "Chicken Parm", "recipe-4.png");
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(11L), 1D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(6L), 0.67));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(4L), 0.5));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(2L), 0.25));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(5L), 0.2));
    recipe.addDirection("Cover pan in oil and heat to sizzle");
    recipe.addDirection("Cover raw chicken in bread crumbs and then cook in oiled pan");
    recipe.addDirection("Preheat oven to 425 degrees");
    recipe.addDirection("Cover cooked chicken in sauce and sprinkle cheese to tasting");
    recipe.addDirection("Bake for 10 minutes");
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(2L, 6L, 3L));

    recipe = new Recipe(5L, "Chicken Enchilada", "recipe-5.png");
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(11L), 0.75));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(9L), 0.33));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(10L), 1D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(2L), 0.25));
    recipe.addDirection("Cook chicken in preferred method");
    recipe.addDirection("Dice or shred chicken");
    recipe.addDirection("Add mozzarella, mushrooms, and chicken to tortilla");
    recipe.addDirection("Wrap and heat in pan for 1 minute before serving");
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(3L, 4L, 1L));

    recipe = new Recipe(6L, "Chicken Marsala", "recipe-6.png");
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(11L), 1.25));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(9L), 0.34));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(5L), 0.2));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(12L), 0.5));
    recipe.addDirection("Cover pan in oil and heat to sizzle");
    recipe.addDirection("Heat chicken in pan for 3 minutes then flip and heat for 3 additional minutes");
    recipe.addDirection("Add mushrooms and chicken broth to pan for an additional minute");
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

  public static IngredientItem getIngredientItem(Long id) {
    return ingredientItemMap.get(id);
  }

}
