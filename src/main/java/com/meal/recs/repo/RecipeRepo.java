package com.meal.recs.repo;

import com.meal.recs.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.meal.recs.model.IngredientUnit.*;

/**
 * Created by gardiary on 25/01/19.
 */
public class RecipeRepo {
  private static List<Recipe> recipes;
  private static Map<Long, Recipe> recipeMap;
  private static Map<Long, List<Long>> recommendationRecipes;
  private static Map<Long, IngredientItem> ingredientItemMap;
  private static Map<Long, IngredientPackage> ingredientPackageMap;

  static {
    recipes = new ArrayList<>();
    recipeMap = new HashMap<>();
    recommendationRecipes = new HashMap<>();

    ingredientItemMap = new HashMap<>();
    /*ingredientItemMap.put(1L, new IngredientItem(1L, "Pasta", "box"));
    ingredientItemMap.put(2L, new IngredientItem(2L, "Mozzarella", "cups"));
    ingredientItemMap.put(3L, new IngredientItem(3L, "Ground Beef", "lbs"));
    ingredientItemMap.put(4L, new IngredientItem(4L, "Red Sauce", "oz"));
    ingredientItemMap.put(5L, new IngredientItem(5L, "Olive Oil", "tbsp"));
    ingredientItemMap.put(6L, new IngredientItem(6L, "Bread Crumbs", "cups"));
    ingredientItemMap.put(7L, new IngredientItem(7L, "Tomatoes", ""));
    ingredientItemMap.put(8L, new IngredientItem(8L, "Lettuce", "cups"));
    ingredientItemMap.put(9L, new IngredientItem(9L, "Mushrooms", "oz sliced"));
    ingredientItemMap.put(10L, new IngredientItem(10L, "Tortillas", "pcs"));
    ingredientItemMap.put(11L, new IngredientItem(11L, "Chicken", "pcs"));
    ingredientItemMap.put(12L, new IngredientItem(12L, "Chicken Broth", "cups"));*/
    ingredientItemMap.put(1L, new IngredientItem(1L, "Pasta", BOX));
    ingredientItemMap.put(2L, new IngredientItem(2L, "Mozzarella", CUP));
    ingredientItemMap.put(3L, new IngredientItem(3L, "Ground Beef", LBS));
    ingredientItemMap.put(4L, new IngredientItem(4L, "Red Sauce", OZ));
    ingredientItemMap.put(5L, new IngredientItem(5L, "Olive Oil", TABLESPOON));
    ingredientItemMap.put(6L, new IngredientItem(6L, "Bread Crumbs", CUP));
    ingredientItemMap.put(7L, new IngredientItem(7L, "Tomatoes", PIECE));
    ingredientItemMap.put(8L, new IngredientItem(8L, "Lettuce", CUP));
    ingredientItemMap.put(9L, new IngredientItem(9L, "Mushrooms", OZ));
    ingredientItemMap.put(10L, new IngredientItem(10L, "Tortillas", PIECE));
    ingredientItemMap.put(11L, new IngredientItem(11L, "Chicken", PIECE));
    ingredientItemMap.put(12L, new IngredientItem(12L, "Chicken Broth", CUP));

    ingredientPackageMap = new HashMap<>();
    ingredientPackageMap.put(1L, new IngredientPackage(1L, "Box", ingredientItemMap.get(1L), 1D));
    ingredientPackageMap.put(2L, new IngredientPackage(2L, "Bag", ingredientItemMap.get(2L), 1D));
    ingredientPackageMap.put(3L, new IngredientPackage(3L, "Lb", ingredientItemMap.get(3L), 1D));
    ingredientPackageMap.put(4L, new IngredientPackage(4L, "Jar", ingredientItemMap.get(4L), 14D));
    ingredientPackageMap.put(5L, new IngredientPackage(5L, "Bottle", ingredientItemMap.get(5L), 34D));
    ingredientPackageMap.put(6L, new IngredientPackage(6L, "Can", ingredientItemMap.get(6L), 1.5));
    ingredientPackageMap.put(7L, new IngredientPackage(7L, "", ingredientItemMap.get(7L), 1D));
    ingredientPackageMap.put(8L, new IngredientPackage(8L, "Head", ingredientItemMap.get(8L), 6D));
    ingredientPackageMap.put(9L, new IngredientPackage(9L, "Carton", ingredientItemMap.get(9L), 12D));
    ingredientPackageMap.put(10L, new IngredientPackage(10L, "Bag", ingredientItemMap.get(10L), 8D));
    ingredientPackageMap.put(11L, new IngredientPackage(11L, "Lb", ingredientItemMap.get(11L), 6D));
    ingredientPackageMap.put(12L, new IngredientPackage(12L, "Carton", ingredientItemMap.get(12L), 4D));

    Recipe recipe = new Recipe(1L, "Baked Ziti", "recipe-1.png");
    recipe.setDescription("Baked Ziti - classic Italian American comfort food of pasta baked with ground beef, tomato sauce and all kinds of gooey, yummy cheeses. So EASY and so good!");
    recipe.setPrepTime(new Time(20, TimeUnit.MINUTE));
    recipe.setCookTime(new Time(35, TimeUnit.MINUTE));
    recipe.setServings(10);
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(1L), 1D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(2L), 1.5));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(3L), 1.25));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(4L), 20D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(5L), 2D));
    recipe.addDirection("Boil water and cook pasta for 10 minutes");
    recipe.addDirection("Cook meat until brown");
    recipe.addDirection("Preheat oven to 350 degrees");
    recipe.addDirection("Oil baking pan and mix sauce, cheese, pasta, and meat and cook for 20 minutes");
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(2L, 4L, 6L));

    recipe = new Recipe(2L, "Meatballs", "recipe-2.png");
    recipe.setDescription("Tender, juicy meatballs loaded with flavor and simmered in a homemade marinara sauce. Perfect for topping spaghetti noodles, or just enjoying as an appetizer.");
    recipe.setPrepTime(new Time(30, TimeUnit.MINUTE));
    recipe.setCookTime(new Time(20, TimeUnit.MINUTE));
    recipe.setServings(4);
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(3L), 0.75));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(2L), 0.5));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(4L), 7D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(6L), 1D));
    recipe.addDirection("Mix cheese and bread crumbs in raw meat");
    recipe.addDirection("Place handful size balls of meat on a baking sheet");
    recipe.addDirection("Preheat oven to 350 degrees");
    recipe.addDirection("Bake for 30 minutes");
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(3L, 5L, 1L));

    recipe = new Recipe(3L, "Tacos", "recipe-3.png");
    recipe.setDescription("Classic, delicious, and a million times better than fast-food tacos, ... An easy 20-minute meal even the pickiest of eaters will enjoy this taco recipe!");
    recipe.setPrepTime(new Time(5, TimeUnit.MINUTE));
    recipe.setCookTime(new Time(15, TimeUnit.MINUTE));
    recipe.setServings(4);
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(3L), 1.25));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(2L), 1.5));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(7L), 2D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(8L), 2D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(9L), 4D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(10L), 8D));
    recipe.addDirection("Brown meat over medium heat");
    recipe.addDirection("Season beef to tasting");
    recipe.addDirection("Heat tortillas for 11 seconds before making taco");
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(6L, 2L, 5L));

    recipe = new Recipe(4L, "Chicken Parm", "recipe-4.png");
    recipe.setDescription("A delicious Italian breaded chicken smothered with cheese and tomato-based pasta sauce!");
    recipe.setPrepTime(new Time(25, TimeUnit.MINUTE));
    recipe.setCookTime(new Time(20, TimeUnit.MINUTE));
    recipe.setServings(6);
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(11L), 6D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(6L), 2D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(4L), 7D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(2L), 0.5));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(5L), 4D));
    recipe.addDirection("Cover pan in oil and heat to sizzle");
    recipe.addDirection("Cover raw chicken in bread crumbs and then cook in oiled pan");
    recipe.addDirection("Preheat oven to 425 degrees");
    recipe.addDirection("Cover cooked chicken in sauce and sprinkle cheese to tasting");
    recipe.addDirection("Bake for 10 minutes");
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(2L, 6L, 3L));

    recipe = new Recipe(5L, "Chicken Enchilada", "recipe-5.png");
    recipe.setDescription("A classic Mexican staple for mushroom lovers.");
    recipe.setPrepTime(new Time(30, TimeUnit.MINUTE));
    recipe.setCookTime(new Time(30, TimeUnit.MINUTE));
    recipe.setServings(5);
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(11L), 5D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(9L), 4D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(10L), 8D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(2L), 0.5));
    recipe.addDirection("Cook chicken in preferred method");
    recipe.addDirection("Dice or shred chicken");
    recipe.addDirection("Add mozzarella, mushrooms, and chicken to tortilla");
    recipe.addDirection("Wrap and heat in pan for 1 minute before serving");
    recipes.add(recipe);
    recipeMap.put(recipe.getId(), recipe);
    recommendationRecipes.put(recipe.getId(), Arrays.asList(3L, 4L, 1L));

    recipe = new Recipe(6L, "Chicken Marsala", "recipe-6.png");
    recipe.setDescription("Chicken Marsala in a deliciously creamy mushroom sauce rivals any restaurant!");
    recipe.setPrepTime(new Time(10, TimeUnit.MINUTE));
    recipe.setCookTime(new Time(20, TimeUnit.MINUTE));
    recipe.setServings(7);
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(11L), 7D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(9L), 4D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(5L), 4D));
    recipe.addIngredient(new Ingredient(ingredientItemMap.get(12L), 4D));
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

  public static IngredientPackage getIngredientPackage(Long id) {
    return ingredientPackageMap.get(id);
  }

}
