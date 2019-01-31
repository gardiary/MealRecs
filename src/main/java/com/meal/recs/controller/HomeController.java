package com.meal.recs.controller;

import com.meal.recs.model.Ingredient;
import com.meal.recs.model.Recipe;
import com.meal.recs.model.RecipeList;
import com.meal.recs.repo.RecipeRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by gardiary on 22/01/19.
 */
@Controller
@SessionAttributes("recipeList")
public class HomeController {

    @ModelAttribute("recipeList")
    public RecipeList recipeList() {
        return new RecipeList();
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("recipes", RecipeRepo.getMultiLinesRecipes(3));

        return "home";
    }

    @RequestMapping(path = "/recipe/{id}", method = RequestMethod.GET)
    public String recipe(@PathVariable("id") Long id, Model model, @ModelAttribute("recipeList") RecipeList recipeList) {
        model.addAttribute("recipe", RecipeRepo.getRecipe(id));
        model.addAttribute("recommendationRecipes", RecipeRepo.getRecommendationRecipes(id));
        model.addAttribute("recipeList", recipeList);

        return "recipe";
    }

    @RequestMapping(path = "/recipe/{id}", method = RequestMethod.POST)
    public String recipePost(@PathVariable("id") Long id, Model model, @RequestParam(value = "ingredient", required = false) List<Long> selectedIngredients,
                             @ModelAttribute("recipeList") RecipeList recipeList) {
        Recipe recipe = RecipeRepo.getRecipe(id);

        if(selectedIngredients != null && selectedIngredients.size() > 0) {
            manageIngredients(recipe, selectedIngredients);

            int size1 = recipeList.getRecipeMap().size();
            recipeList.addRecipe(recipe);
            int size2 = recipeList.getRecipeMap().size();

            if(size1 == size2) {
                model.addAttribute("message", "Recipe successfully updated");
            } else {
                model.addAttribute("message", "Recipe successfully added");
            }
        }

        model.addAttribute("recipe", RecipeRepo.getRecipe(id));
        model.addAttribute("recommendationRecipes", RecipeRepo.getRecommendationRecipes(id));
        model.addAttribute("recipeList", recipeList);

        return "recipe";
    }

    @RequestMapping(path = "/recipe/list", method = RequestMethod.GET)
    public String recipeList(Model model, @ModelAttribute("recipeList") RecipeList recipeList) {
        model.addAttribute("recipeList", recipeList);

        Random random = new Random();
        if(recipeList.getRecipeMap().size() > 0) {
            Set<Long> keySet = recipeList.getRecipeMap().keySet();
            List<Long> keys = new ArrayList<>(keySet);
            Long id = keys.get(random.nextInt(recipeList.getRecipeMap().size()));

            model.addAttribute("recommendationRecipes", RecipeRepo.getRecommendationRecipes(id));
        } else {
            Integer id = random.nextInt(6) + 1;

            model.addAttribute("recommendationRecipes", RecipeRepo.getRecommendationRecipes(Long.valueOf(id)));
        }

        return "recipeList";
    }

    @RequestMapping(path = "/recipe/list/remove", method = RequestMethod.GET)
    public String recipeListRemove(@RequestParam("id") Long id, @ModelAttribute("recipeList") RecipeList recipeList, Model model) {
        recipeList.removeRecipe(id);

        return "redirect:/recipe/list";
    }

    private void manageIngredients(Recipe recipe, List<Long> selectedIngredients) {
        for(Long key : recipe.getIngredients().keySet()) {
            Ingredient ingredient = recipe.getIngredients().get(key);
            boolean selected = false;

            for(Long selectedIngredient : selectedIngredients) {
                if(ingredient.getItem().getId().equals(selectedIngredient)) {
                    selected = true;
                    break;
                }
            }

            ingredient.setSelected(selected);
        }
    }
}
