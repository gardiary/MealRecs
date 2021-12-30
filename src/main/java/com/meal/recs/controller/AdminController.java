package com.meal.recs.controller;

import com.meal.recs.data.entity.IngredientItemEntity;
import com.meal.recs.model.IngredientItem;
import com.meal.recs.model.IngredientUnit;
import com.meal.recs.model.Source;
import com.meal.recs.navigator.PageNavigator;
import com.meal.recs.service.HttpService;
import com.meal.recs.service.IngredientItemService;
import com.meal.recs.spoonacular.SearchIngredientResponse;
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

    @Value("${spoonacular.api.get.ingredient}")
    private String SPOONACULAR_API_GET_INGREDIENT;

    @Autowired
    private IngredientItemService ingredientItemService;

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
            /*List<IngredientItem> ingredients = new ArrayList<>();
            ingredients.add(new IngredientItem(23572L, "beef", SPOONACULAR_BASE_INGREDIENT_IMAGE_URL + "/beef-cubes-raw.png"));
            ingredients.add(new IngredientItem(5006L, "chicken", SPOONACULAR_BASE_INGREDIENT_IMAGE_URL + "/whole-chicken.jpg"));
            ingredients.add(new IngredientItem(10011282L, "red onion", SPOONACULAR_BASE_INGREDIENT_IMAGE_URL + "/red-onion.png"));
            ingredients.add(new IngredientItem(1072047L, "onion salt", SPOONACULAR_BASE_INGREDIENT_IMAGE_URL + "/garlic-salt.png"));
            ingredients.add(new IngredientItem(11529L, "tomato", SPOONACULAR_BASE_INGREDIENT_IMAGE_URL + "/tomato.png"));*/

            model.addAttribute(INGREDIENTS, response.getResults());
            model.addAttribute(BASE_IMAGE_URL, SPOONACULAR_BASE_INGREDIENT_IMAGE_URL);
        }

        return "admin/ingredient_search";
    }

    @GetMapping("/spoonacular/ingredient")
    public String spoonacularIngredientGet(Model model, @RequestParam String id) {
        IngredientItemEntity entity = ingredientItemService.get(Source.SPOONACULAR, id);
        if(entity != null) {
            return "redirect:/admin/ingredient?id=" + entity.getId();
        }

        Map<String, String> params = new HashMap<>();
        params.put(API_KEY, SPOONACULAR_API_KEY);
        params.put(ID, id);

        com.meal.recs.spoonacular.IngredientItem ingredientItem = httpService.get(SPOONACULAR_API_GET_INGREDIENT, com.meal.recs.spoonacular.IngredientItem.class, params);
        LOGGER.info("ingredientItem : {}", ingredientItem);

        model.addAttribute(INGREDIENT, ingredientItem);
        model.addAttribute(BASE_IMAGE_URL, SPOONACULAR_BASE_INGREDIENT_IMAGE_URL);

        return "admin/spoonacular_ingredient";
    }

    @PostMapping("/spoonacular/ingredient")
    public String spoonacularIngredientPost(@ModelAttribute("ingredient") com.meal.recs.spoonacular.IngredientItem ingredientItem) {
        LOGGER.info("IngredientItem : {}", ingredientItem);

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
    public String ingredientPost(@ModelAttribute("ingredient") IngredientItem ingredientItem, Model model, @RequestParam Long id) {
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
}
