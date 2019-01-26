package com.meal.recs.controller;

import com.meal.recs.repo.RecipeRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gardiary on 22/01/19.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("recipes", RecipeRepo.getMultiLinesRecipes(3));

        return "home";
    }

    @RequestMapping(path = "/recipe/{id}", method = RequestMethod.GET)
    public String recipe(@PathVariable("id") Long id, Model model){
        model.addAttribute("recipe", RecipeRepo.getRecipe(id));
        model.addAttribute("recommendationRecipes", RecipeRepo.getRecommendationRecipes(id));
        return "recipe";
    }

}
