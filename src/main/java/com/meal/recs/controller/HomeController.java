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

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

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

        return "recipeList";
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

    protected void printParameters(HttpServletRequest request) {
        System.out.println("All parameters:");
        System.out.println("---------------");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();
            System.out.println("Param Name : " + paramName);

            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                System.out.println("Value : " + paramValue);
            }

        }
    }

}
