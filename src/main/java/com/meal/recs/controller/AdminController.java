package com.meal.recs.controller;

import com.meal.recs.data.entity.IngredientEntity;
import com.meal.recs.data.entity.IngredientItemEntity;
import com.meal.recs.data.entity.IngredientPackageEntity;
import com.meal.recs.data.entity.RecipeEntity;
import com.meal.recs.model.*;
import com.meal.recs.navigator.PageNavigator;
import com.meal.recs.repo.RecipeRepo;
import com.meal.recs.service.*;
import com.meal.recs.spoonacular.RecipeInstructionStep;
import com.meal.recs.spoonacular.SearchIngredientResponse;
import com.meal.recs.spoonacular.SearchRecipeResponse;
import com.meal.recs.utility.Utilities;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.meal.recs.utility.Constants.*;

/**
 * User: gardiary
 * Date: 28/12/21, 07.08
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${spoonacular.api.key}")
    private String SPOONACULAR_API_KEY;

    @Value("${spoonacular.base.ingredient.image.url}")
    private String SPOONACULAR_BASE_INGREDIENT_IMAGE_URL;

    @Value("${spoonacular.api.search.ingredient}")
    private String SPOONACULAR_API_SEARCH_INGREDIENT;

    @Value("${spoonacular.api.search.recipe}")
    private String SPOONACULAR_API_SEARCH_RECIPE;

    @Value("${spoonacular.api.get.ingredient}")
    private String SPOONACULAR_API_GET_INGREDIENT;

    @Value("${spoonacular.api.get.recipe}")
    private String SPOONACULAR_API_GET_RECIPE;

    @Autowired
    private IngredientItemService ingredientItemService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private IngredientPackageService ingredientPackageService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private HttpService httpService;

    @Autowired
    protected PageNavigator pageNavigator;

    @GetMapping("/ingredient/search")
    public String ingredientSearch(Model model, @RequestParam(required = false) String query) {
        if (ObjectUtils.isNotEmpty(query)) {
            Map<String, String> params = new HashMap<>();
            params.put(API_KEY, SPOONACULAR_API_KEY);
            params.put(QUERY, query.trim());

            SearchIngredientResponse response = httpService.get(SPOONACULAR_API_SEARCH_INGREDIENT, SearchIngredientResponse.class, params);
            model.addAttribute(INGREDIENTS, response.getResults());
            model.addAttribute(BASE_IMAGE_URL, SPOONACULAR_BASE_INGREDIENT_IMAGE_URL);
        }

        return "admin/ingredient_search";
    }

    @GetMapping("/spoonacular/ingredient")
    public String spoonacularIngredientGet(Model model, @RequestParam String id, @RequestParam(name = "defUnit", required = false) IngredientUnit unit) {
        IngredientItemEntity entity = ingredientItemService.get(Source.SPOONACULAR, id);
        if(entity != null) {
            return "redirect:/admin/ingredient?id=" + entity.getId();
        }

        Map<String, String> params = new HashMap<>();
        params.put(API_KEY, SPOONACULAR_API_KEY);
        params.put(ID, id);

        com.meal.recs.spoonacular.IngredientItem ingredientItem = httpService.get(SPOONACULAR_API_GET_INGREDIENT, com.meal.recs.spoonacular.IngredientItem.class, params);

        model.addAttribute(INGREDIENT, ingredientItem);
        model.addAttribute(BASE_IMAGE_URL, SPOONACULAR_BASE_INGREDIENT_IMAGE_URL);
        model.addAttribute(UNITS, IngredientUnit.values());
        model.addAttribute(UNIT, unit);

        return "admin/spoonacular_ingredient";
    }

    @PostMapping("/spoonacular/ingredient")
    public String spoonacularIngredientPost(@ModelAttribute("ingredient") com.meal.recs.spoonacular.IngredientItem ingredientItem) {
        if(ObjectUtils.isNotEmpty(ingredientItem.getName())) {
            ingredientItemService.save(ingredientItem.getName().trim(), IngredientUnit.valueOf(ingredientItem.getUnit().toUpperCase()),
                    SPOONACULAR_BASE_INGREDIENT_IMAGE_URL + ingredientItem.getImage().trim(), Source.SPOONACULAR, String.valueOf(ingredientItem.getId()));
        }

        return "redirect:/admin/ingredients";
    }

    @GetMapping("/ingredients")
    public String ingredients(Model model, @RequestParam(name = "p", required = false) Integer page, HttpServletRequest request) {
        Long recordCount = ingredientItemService.countAll();
        List<IngredientItemEntity> ingredientItems = new ArrayList<>();
        page = page == null || page <= 0 ? 1 : page;

        if(recordCount > 0) {
            ingredientItems = ingredientItemService.findAll(page, PAGINATION_PAGE_SIZE);

            String pageNav = pageNavigator.buildPageNav(request.getRequestURI(),
                    recordCount, page, PAGINATION_PAGE_SIZE, PAGINATION_NAV_TRAIL);
            model.addAttribute("pageNav", pageNav);
            model.addAttribute("page", page);
            model.addAttribute("pageSize", PAGINATION_PAGE_SIZE);
        }

        model.addAttribute(INGREDIENTS, ingredientItems);
        model.addAttribute(BASE_IMAGE_URL, SPOONACULAR_BASE_INGREDIENT_IMAGE_URL);

        return "admin/ingredients";
    }

    @GetMapping("/ingredient")
    public String ingredientGet(Model model, @RequestParam Long id) {
        IngredientItemEntity entity = ingredientItemService.get(id);
        if(entity == null) {
            return "redirect:/admin/ingredients";
        }

        model.addAttribute(INGREDIENT, new IngredientItem(entity));
        model.addAttribute(UNITS, IngredientUnit.values());
        model.addAttribute(SOURCES, Source.values());
        return "admin/ingredient_item";
    }

    @PostMapping("/ingredient")
    public String ingredientPost(@ModelAttribute("ingredient") IngredientItem ingredientItem, @RequestParam Long id) {
        IngredientItemEntity entity = ingredientItemService.get(id);
        if(entity == null) {
            return "redirect:/admin/ingredients";
        }

        if(ObjectUtils.isNotEmpty(ingredientItem.getName())) {
            entity.setName(ingredientItem.getName().trim());
            entity.setUnit(ingredientItem.getUnit());
            entity.setImage(ingredientItem.getImage().trim());
            entity.setSource(ingredientItem.getSource());
            ingredientItemService.save(entity);
        }

        return "redirect:/admin/ingredient?id=" + id;
    }

    @GetMapping("/ingredient/delete")
    public String ingredientDelete(@RequestParam Long id) {
        ingredientItemService.delete(id);
        return "redirect:/admin/ingredients";
    }

    @GetMapping("/recipe/search")
    public String recipeSearch(Model model, @RequestParam(required = false) String query) {
        if (ObjectUtils.isNotEmpty(query)) {
            Map<String, String> params = new HashMap<>();
            params.put(API_KEY, SPOONACULAR_API_KEY);
            params.put(QUERY, query.trim());

            SearchRecipeResponse response = httpService.get(SPOONACULAR_API_SEARCH_RECIPE, SearchRecipeResponse.class, params);
            model.addAttribute(RECIPES, response.getResults());
        }

        return "admin/recipe_search";
    }

    @GetMapping("/spoonacular/recipe")
    public String spoonacularRecipeGet(Model model, @RequestParam String id) {
        RecipeEntity entity = recipeService.get(Source.SPOONACULAR, id);
        if(entity != null) {
            return "redirect:/admin/recipe?id=" + entity.getId();
        }

        Map<String, String> params = new HashMap<>();
        params.put(API_KEY, SPOONACULAR_API_KEY);
        params.put(ID, id);

        com.meal.recs.spoonacular.Recipe spoonRecipe = httpService.get(SPOONACULAR_API_GET_RECIPE, com.meal.recs.spoonacular.Recipe.class, params);
        if(ObjectUtils.isNotEmpty(spoonRecipe.getInstructions())) {
            spoonRecipe.setDirections(spoonRecipe.getInstructions().get(0).getSteps());
        }

        boolean ingredientsComplete = true;
        for(com.meal.recs.spoonacular.Ingredient ingredient : spoonRecipe.getIngredients()) {
            if(ingredient.getId() != null) {
                IngredientItemEntity ingItem = ingredientItemService.get(Source.SPOONACULAR, String.valueOf(ingredient.getId()));
                ingredient.setExists(ingItem != null);
                ingredient.setAppId(ingItem != null ? ingItem.getId() : null);

                if (ingItem == null) {
                    ingredientsComplete = false;
                }
            }
        }

        model.addAttribute(RECIPE, spoonRecipe);
        model.addAttribute(BASE_IMAGE_URL, SPOONACULAR_BASE_INGREDIENT_IMAGE_URL);
        model.addAttribute(UNITS, IngredientUnit.values());
        model.addAttribute("ingredientsComplete", ingredientsComplete);

        return "admin/spoonacular_recipe";
    }

    @PostMapping("/spoonacular/recipe")
    public String spoonacularRecipePost(@ModelAttribute("recipe") com.meal.recs.spoonacular.Recipe recipe) {
        if(ObjectUtils.isNotEmpty(recipe.getName())) {
            RecipeEntity entity = new RecipeEntity();
            entity.setName(recipe.getName());
            entity.setDescription(recipe.getDescription());
            if(recipe.getPreparationInMinutes() != null) {
                entity.setPrepTime(recipe.getPreparationInMinutes());
                entity.setPrepTimeUnit(TimeUnit.MINUTE);
            }
            if(recipe.getReadyInMinutes() != null) {
                entity.setCookTime(recipe.getReadyInMinutes());
                entity.setCookTimeUnit(TimeUnit.MINUTE);
            }
            entity.setServings(recipe.getServings());
            entity.setSource(Source.SPOONACULAR);
            entity.setSourceUrl(recipe.getSourceUrl());
            entity.setImage(recipe.getImage());
            entity.setKeyword(recipe.getKeyword());
            entity.setExtId(String.valueOf(recipe.getId()));
            if(ObjectUtils.isNotEmpty(recipe.getDirections())) {
                List<String> directionList = recipe.getDirections().stream().map(RecipeInstructionStep::getStep).collect(Collectors.toList());
                entity.setDirections(Utilities.objToJsonString(directionList));
            }
            recipeService.save(entity);

            // save ingredients
            for(com.meal.recs.spoonacular.Ingredient ingredient : recipe.getIngredients()) {
                IngredientEntity ingredientEntity = new IngredientEntity();
                ingredientEntity.setRecipeId(entity.getId());
                ingredientEntity.setItem(ingredientItemService.get(Source.SPOONACULAR, String.valueOf(ingredient.getId())));
                ingredientEntity.setAmount(ingredient.getAmount());
                if(ObjectUtils.isNotEmpty(ingredient.getUnit())) {
                    ingredientEntity.setUnit(IngredientUnit.valueOf(ingredient.getUnit()));
                }
                ingredientService.save(ingredientEntity);
            }
        }

        return "redirect:/admin/recipes";
    }

    @GetMapping("/recipes")
    public String recipes(Model model, @RequestParam(name = "p", required = false) Integer page, HttpServletRequest request) {
        Long recordCount = recipeService.countAll();
        List<RecipeEntity> recipes = new ArrayList<>();
        page = page == null || page <= 0 ? 1 : page;

        if(recordCount > 0) {
            recipes = recipeService.findAll(page, PAGINATION_PAGE_SIZE);

            String pageNav = pageNavigator.buildPageNav(request.getRequestURI(),
                    recordCount, page, PAGINATION_PAGE_SIZE, PAGINATION_NAV_TRAIL);
            model.addAttribute("pageNav", pageNav);
            model.addAttribute("page", page);
            model.addAttribute("pageSize", PAGINATION_PAGE_SIZE);
        }

        model.addAttribute(RECIPES, recipes);
        return "admin/recipes";
    }

    @GetMapping("/recipe")
    public String recipeGet(Model model, @RequestParam Long id) {
        RecipeEntity entity = recipeService.get(id);
        if(entity == null) {
            return "redirect:/admin/recipes";
        }

        Recipe recipe = new Recipe();
        recipe.setId(entity.getId());
        recipe.setName(entity.getName());
        recipe.setDescription(entity.getDescription());
        recipe.setPrepTime(new Time(entity.getPrepTime(), entity.getPrepTimeUnit()));
        recipe.setCookTime(new Time(entity.getCookTime(), entity.getCookTimeUnit()));
        recipe.setServings(entity.getServings());
        recipe.setSource(entity.getSource());
        recipe.setSourceUrl(entity.getSourceUrl());
        recipe.setImage(entity.getImage());
        recipe.setKeyword(entity.getKeyword());
        recipe.setExtId(entity.getExtId());

        if(ObjectUtils.isNotEmpty(entity.getDirections())) {
            recipe.setDirections(Utilities.jsonStringToObj(entity.getDirections(), List.class));
        }

        for(IngredientEntity ingredientEntity : entity.getIngredients()) {
            recipe.getIngredients().add(new Ingredient(ingredientEntity));
        }

        model.addAttribute(RECIPE, recipe);
        model.addAttribute(SOURCES, Source.values());
        model.addAttribute(UNITS, IngredientUnit.values());
        model.addAttribute("timeUnits", TimeUnit.values());

        return "admin/recipe";
    }

    @PostMapping("/recipe")
    public String spoonacularRecipePost(@ModelAttribute("recipe") Recipe recipe, @RequestParam Long id) {
        RecipeEntity entity = recipeService.get(id);
        if(entity == null) {
            return "redirect:/admin/recipes";
        }

        if(ObjectUtils.isNotEmpty(recipe.getName())) {
            entity.setName(recipe.getName());
            entity.setDescription(recipe.getDescription());
            entity.setPrepTime(recipe.getPrepTime().getValue());
            entity.setPrepTimeUnit(recipe.getPrepTime().getTimeUnit());
            entity.setCookTime(recipe.getCookTime().getValue());
            entity.setCookTimeUnit(recipe.getCookTime().getTimeUnit());
            entity.setServings(recipe.getServings());
            entity.setSource(recipe.getSource());
            entity.setSourceUrl(recipe.getSourceUrl());
            entity.setImage(recipe.getImage());
            entity.setKeyword(recipe.getKeyword());
            entity.setDirections(Utilities.objToJsonString(recipe.getDirections()));
            //entity.setExtId(String.valueOf(recipe.getId()));
            recipeService.save(entity);

            // save ingredients
            for(Ingredient ingredient : recipe.getIngredients()) {
                IngredientEntity ingredientEntity = ingredientService.get(ingredient.getId());
                ingredientEntity.setAmount(ingredient.getAmount());
                ingredientEntity.setUnit(ingredient.getUnit());
                ingredientService.save(ingredientEntity);
            }
        }

        return "redirect:/admin/recipe?id=" + id;
    }

    @GetMapping("/recipe/delete")
    public String recipeDelete(@RequestParam Long id) {
        RecipeEntity entity = recipeService.get(id);
        if(entity == null) {
            return "redirect:/admin/recipes";
        }

        ingredientService.deleteAll(entity.getIngredients());
        recipeService.delete(entity);
        return "redirect:/admin/recipes";
    }

    @GetMapping("/ingredient_package/add")
    public String ingredientPackageAddGet(Model model) {
        IngredientPackage ingredientPackage = new IngredientPackage();
        ingredientPackage.setItem(new IngredientItem());

        model.addAttribute("ingredientPackage", ingredientPackage);
        model.addAttribute(UNITS, PackageUnit.values());
        model.addAttribute(INGREDIENTS, ingredientItemService.findAll());
        return "admin/ingredient_package";
    }

    @PostMapping("/ingredient_package/add")
    public String ingredientPackageAddPost(@ModelAttribute IngredientPackage ingredientPackage, Model model) {
        if(ingredientPackage.getUnit() == null || ingredientPackage.getItemAmount() == null
                || ingredientPackage.getItem() == null || ingredientPackage.getItem().getId() == null) {
            model.addAttribute("ingredientPackage", ingredientPackage);
            model.addAttribute(UNITS, PackageUnit.values());
            model.addAttribute(INGREDIENTS, ingredientItemService.findAll());
            model.addAttribute("message", "Please complete all fields");
            return "admin/ingredient_package";
        }

        IngredientPackageEntity entity = new IngredientPackageEntity(ingredientPackage);
        IngredientPackageEntity checkEntity = ingredientPackageService.get(entity.getItem());
        if(checkEntity != null) {
            model.addAttribute("ingredientPackage", ingredientPackage);
            model.addAttribute(UNITS, PackageUnit.values());
            model.addAttribute(INGREDIENTS, ingredientItemService.findAll());
            model.addAttribute("message", "Ingredient package already exists");
            return "admin/ingredient_package";
        }

        ingredientPackageService.save(entity);

        entity.setItem(ingredientItemService.get(ingredientPackage.getItem().getId()));
        RecipeRepo.addIngredientPackage(new IngredientPackage(entity));
        return "redirect:/admin/ingredient_packages";
    }

    @GetMapping("/ingredient_package")
    public String ingredientPackageGet(Model model, @RequestParam Long id) {
        IngredientPackageEntity entity = ingredientPackageService.get(id);
        if(entity == null) {
            return "redirect:/admin/ingredient_packages";
        }

        model.addAttribute("ingredientPackage", new IngredientPackage(entity));
        model.addAttribute(UNITS, PackageUnit.values());
        model.addAttribute(INGREDIENTS, ingredientItemService.findAll());
        return "admin/ingredient_package";
    }

    @PostMapping("/ingredient_package")
    public String ingredientPackagePost(@ModelAttribute IngredientPackage ingredientPackage, Model model, @RequestParam Long id) {
        if(ingredientPackage.getUnit() == null || ingredientPackage.getItemAmount() == null
                || ingredientPackage.getItem() == null || ingredientPackage.getItem().getId() == null) {
            model.addAttribute("ingredientPackage", ingredientPackage);
            model.addAttribute(UNITS, PackageUnit.values());
            model.addAttribute(INGREDIENTS, ingredientItemService.findAll());
            model.addAttribute("message", "Please complete all fields");
            return "admin/ingredient_package";
        }

        IngredientPackageEntity entity = ingredientPackageService.get(id);
        if(entity == null) {
            return "redirect:/admin/ingredient_packages";
        }

        entity.setUnit(ingredientPackage.getUnit());
        entity.setItem(new IngredientItemEntity(ingredientPackage.getItem().getId()));
        entity.setItemAmount(ingredientPackage.getItemAmount());

        IngredientPackageEntity checkEntity = ingredientPackageService.get(entity.getItem());
        if(checkEntity != null && !checkEntity.getId().equals(entity.getId())) {
            model.addAttribute("ingredientPackage", ingredientPackage);
            model.addAttribute(UNITS, PackageUnit.values());
            model.addAttribute(INGREDIENTS, ingredientItemService.findAll());
            model.addAttribute("message", "Ingredient package already exists");
            return "admin/ingredient_package";
        }

        ingredientPackageService.save(entity);

        entity.setItem(ingredientItemService.get(ingredientPackage.getItem().getId()));
        RecipeRepo.addIngredientPackage(new IngredientPackage(entity));
        return "redirect:/admin/ingredient_package?id=" + id;
    }

    @GetMapping("/ingredient_packages")
    public String ingredientPackages(Model model, @RequestParam(name = "p", required = false) Integer page, HttpServletRequest request) {
        Long recordCount = ingredientPackageService.countAll();
        List<IngredientPackageEntity> packages = new ArrayList<>();
        page = page == null || page <= 0 ? 1 : page;

        if(recordCount > 0) {
            packages = ingredientPackageService.findAll(page, PAGINATION_PAGE_SIZE);

            String pageNav = pageNavigator.buildPageNav(request.getRequestURI(),
                    recordCount, page, PAGINATION_PAGE_SIZE, PAGINATION_NAV_TRAIL);
            model.addAttribute("pageNav", pageNav);
            model.addAttribute("page", page);
            model.addAttribute("pageSize", PAGINATION_PAGE_SIZE);
        }

        model.addAttribute(PACKAGES, packages);
        return "admin/ingredient_packages";
    }

    @GetMapping("/ingredient_package/delete")
    public String ingredientPackageDelete(@RequestParam Long id) {
        IngredientPackageEntity entity = ingredientPackageService.get(id);
        RecipeRepo.removeIngredientPackage(new IngredientPackage(entity));

        ingredientPackageService.delete(id);
        return "redirect:/admin/ingredient_packages";
    }
}
