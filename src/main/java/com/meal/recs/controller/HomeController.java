package com.meal.recs.controller;

import com.meal.recs.model.Ingredient;
import com.meal.recs.model.IngredientPackage;
import com.meal.recs.model.Recipe;
import com.meal.recs.model.RecipeList;
import com.meal.recs.repo.RecipeRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by gardiary on 22/01/19.
 */
@Controller
@SessionAttributes("recipeList")
public class HomeController {
    @Value("${server.base.host}")
    private String BASE_HOST = "";

    @ModelAttribute("recipeList")
    public RecipeList recipeList() {
        return new RecipeList();
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("recipes", RecipeRepo.getMultiLinesRecipes(3));

        return "home";
    }

    @GetMapping("/recipes")
    public String recipes(Model model){
        model.addAttribute("recipes", RecipeRepo.getMultiLinesRecipes(3));

        return "recipes";
    }

    @RequestMapping(path = "/recipe/{id}", method = RequestMethod.GET)
    public String recipe(@PathVariable("id") Long id, Model model,
                         @RequestParam(value = "crossCheck", required = false) String crossCheck,
                         @ModelAttribute("recipeList") RecipeList recipeList) {
        Recipe recipe = RecipeRepo.getRecipe(id);

        if(crossCheck != null && crossCheck.equals("1")) {
            Map<Long, Ingredient> totalIngredients = recipeList.getTotalIngredients();

            if(totalIngredients.size() > 0) {
                Map<Long, Ingredient> ingredients = recipe.getIngredientsMap();

                for (Map.Entry<Long, Ingredient> entry : ingredients.entrySet()) {
                    Ingredient ingredient = entry.getValue();
                    Ingredient checkIngredient = totalIngredients.get(ingredient.getItem().getId());

                    if (checkIngredient != null) {
                        IngredientPackage ingredientPackage = RecipeRepo.getIngredientPackage(checkIngredient.getItem().getId());
                        Double total = checkIngredient.getPackageCount() * ingredientPackage.getItemPackage();
                        Double remaining = total - checkIngredient.getAmount();

                        if (ingredient.getAmount() <= remaining) {
                            ingredient.setSelected(false);
                        } else {
                            ingredient.setSelected(true);
                        }
                    } else {
                        ingredient.setSelected(true);
                    }
                }
            }
        } else if(recipeList.getRecipeMap().get(id) == null) {
            recipe.resetIngredientsState();
        }

        model.addAttribute("recipe", recipe);
        model.addAttribute("recommendationRecipes", RecipeRepo.getRecommendationRecipes(id));

        return "recipe";
    }

    @RequestMapping(path = "/recipe/{id}", method = RequestMethod.POST)
    public String recipePost(@PathVariable("id") Long id, Model model, @RequestParam(value = "ingredient", required = false) List<Long> selectedIngredients,
                             @ModelAttribute("recipeList") RecipeList recipeList) {
        Recipe recipe = RecipeRepo.getRecipe(id);

        if(selectedIngredients != null && selectedIngredients.size() > 0) {
            manageIngredients(recipe, selectedIngredients, recipeList);
        } else {    // uncheck all ingredients
            recipe.resetIngredientsState(false);
        }

        int size1 = recipeList.getRecipeMap().size();
        recipeList.addRecipe(recipe);
        int size2 = recipeList.getRecipeMap().size();

        if(size1 == size2) {
            model.addAttribute("message", "Recipe successfully updated");
        } else {
            model.addAttribute("message", "Recipe successfully added");
        }

        model.addAttribute("recipe", RecipeRepo.getRecipe(id));
        model.addAttribute("recommendationRecipes", RecipeRepo.getRecommendationRecipes(id));
        model.addAttribute("recipeList", recipeList);

        return "redirect:/recipe/list";
    }

    @RequestMapping(path = "/recipe/{id}/add", method = RequestMethod.GET)
    public String recipeAdd(@PathVariable("id") Long id,
                            @RequestParam(value = "crossCheck", required = false) String crossCheck,
                            @ModelAttribute("recipeList") RecipeList recipeList) {
        Recipe recipe = RecipeRepo.getRecipe(id);
        recipe.resetIngredientsState();
        /*if(crossCheck != null && crossCheck.equals("1")) {
            Map<Long, Ingredient> totalIngredients = recipeList.getTotalIngredients();

            if(totalIngredients.size() > 0) {
                for (Map.Entry<Long, Ingredient> entry : recipe.getIngredients().entrySet()) {
                    Ingredient ingredient = entry.getValue();
                    Ingredient checkIngredient = totalIngredients.get(ingredient.getItem().getId());

                    if (checkIngredient != null) {
                        IngredientPackage ingredientPackage = RecipeRepo.getIngredientPackage(checkIngredient.getItem().getId());
                        Double total = checkIngredient.getPackageCount() * ingredientPackage.getItemPackage();
                        Double remaining = total - checkIngredient.getAmount();

                        if (ingredient.getAmount() <= remaining) {
                            ingredient.setSelected(false);
                        } else {
                            ingredient.setSelected(true);
                        }
                    } else {
                        ingredient.setSelected(true);
                    }
                }
            }
        } else {
            for (Map.Entry<Long, Ingredient> entry : recipe.getIngredients().entrySet()) {
                Ingredient ingredient = entry.getValue();
                ingredient.setSelected(true);
            }
        }*/

        recipeList.addRecipe(recipe);

        return "redirect:/recipe/list";
    }

    @RequestMapping(path = "/recipe/list", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public String recipeList(Model model, @ModelAttribute("recipeList") RecipeList recipeList) {
        model.addAttribute("recipeList", recipeList);

        List<Recipe> recommendationRecipes = new ArrayList<>();
        Random random = new Random();
        if(recipeList.getRecipeMap().size() > 0) {
            Set<Long> keySet = recipeList.getRecipeMap().keySet();
            //Long id = keys.get(random.nextInt(recipeList.getRecipeMap().size()));

            List<Recipe> recommendationRecipesCandidate = RecipeRepo.getRecipes().stream()
                    .filter(rec -> recipeList.getRecipeMap().get(rec.getId()) == null)
                    .collect(Collectors.toList());

            if(recommendationRecipesCandidate != null && recommendationRecipesCandidate.size() > 0) {
                if(recommendationRecipesCandidate.size() <= 3) {
                    recommendationRecipes = recommendationRecipesCandidate;
                } else {
                    recommendationRecipes = recommendationRecipesCandidate.subList(0, 3);   // pick 3
                }
            }

        } else {
            Integer id = random.nextInt(6) + 1;

            recommendationRecipes = RecipeRepo.getRecommendationRecipes(Long.valueOf(id));
        }

        // determine recommendation recipes ingredients here
        Map<Long, Ingredient> totalIngredients = recipeList.getTotalIngredients();

        if(totalIngredients.size() > 0) {
            /*System.out.println("Total Ingredients:");
            for(Map.Entry<Long, Ingredient> entry : totalIngredients.entrySet()) {
                Ingredient ingredient = entry.getValue();

                IngredientPackage ingredientPackage = RecipeRepo.getIngredientPackage(ingredient.getItem().getId());
                Double totalX = ingredient.getPackageCount() * ingredientPackage.getItemPackage();
                Double remainingX = totalX - ingredient.getAmount();

                System.out.println("- " + ingredient.getItem().getName() + " : " +
                        ingredient.getAmountAsString() + " " + ingredient.getItem().getUnit() + ", " +
                        ingredient.getPackageCountText() + "  (total: " + totalX + ", remaining: " + remainingX + ")");
            }
            System.out.println();*/

            for (Recipe recipe : recommendationRecipes) {
                Map<Long, Ingredient> ingredients = recipe.getIngredientsMap();

                List<Ingredient> neededIngredients = new ArrayList<>();

                for(Map.Entry<Long, Ingredient> entry : ingredients.entrySet()) {
                    Ingredient ingre = entry.getValue();

                    Ingredient checkIngredient = totalIngredients.get(ingre.getItem().getId());

                    if(checkIngredient != null) {
                        IngredientPackage ingredientPackage = RecipeRepo.getIngredientPackage(checkIngredient.getItem().getId());
                        Double total = checkIngredient.getPackageCount() * ingredientPackage.getItemPackage();
                        Double remaining = total - checkIngredient.getAmount();

                        if(ingre.getAmount() <= remaining) {
                            //ingredientAvailable = true;
                        } else {
                            neededIngredients.add(ingre);
                        }
                    } else {
                        neededIngredients.add(ingre);
                    }

                    //System.out.println("- " + ingre.getItem().getName() + " : " +
                    //        ingre.getAmountAsString() + " " + ingre.getItem().getUnit() + "  (available : " + ingredientAvailable + ")");
                }

                if(neededIngredients.size() == 0) {
                    recipe.setRecommendationMessage("Add Recipe");
                } else {
                    if(neededIngredients.size() == 1) {
                        recipe.setRecommendationMessage("Add " + neededIngredients.get(0).getItem().getName());
                    } else {
                        recipe.setRecommendationMessage("Add " + neededIngredients.size() + " ingredients");
                    }
                }

                recipe.setNeededIngredients(neededIngredients);
                //System.out.println();
            }

            // sort by least ingredients
            recommendationRecipes = recommendationRecipes.stream()
                    .sorted((rec1, rec2) -> Integer.valueOf(rec1.getNeededIngredients().size()).
                            compareTo(Integer.valueOf(rec2.getNeededIngredients().size())))
                    .collect(Collectors.toList());
        }

        model.addAttribute("recommendationRecipes", recommendationRecipes);
        model.addAttribute("baseHost", BASE_HOST);

        return "recipe_list";
    }

    @RequestMapping(path = "/recipe/list/remove", method = RequestMethod.GET)
    public String recipeListRemove(@RequestParam("id") Long id, @ModelAttribute("recipeList") RecipeList recipeList) {
        recipeList.removeRecipe(id);

        // reset ingredients state
        Recipe recipe = RecipeRepo.getRecipe(id);
        recipe.resetIngredientsState();

        return "redirect:/recipe/list";
    }

    private void manageIngredients(Recipe recipe, List<Long> selectedIngredients, RecipeList recipeList) {
        for(Long key : recipe.getIngredientsMap().keySet()) {
            Ingredient ingredient = recipe.getIngredientsMap().get(key);
            boolean selected = false;

            for(Long selectedIngredient : selectedIngredients) {
                if(ingredient.getItem().getId().equals(selectedIngredient)) {
                    selected = true;
                    break;
                }
            }

            // cross check, available ingredients should be checked
            if(!selected) {
                Map<Long, Ingredient> totalIngredients = recipeList.getTotalIngredients();

                if (totalIngredients.size() > 0) {
                    Ingredient checkIngredient = totalIngredients.get(ingredient.getItem().getId());

                    if (checkIngredient != null) {
                        IngredientPackage ingredientPackage = RecipeRepo.getIngredientPackage(checkIngredient.getItem().getId());
                        Double total = checkIngredient.getPackageCount() * ingredientPackage.getItemPackage();
                        Double remaining = total - checkIngredient.getAmount();

                        if (ingredient.getAmount() <= remaining) {
                            selected = true;
                        }
                    }
                }
            }

            ingredient.setSelected(selected);
        }
    }
}
