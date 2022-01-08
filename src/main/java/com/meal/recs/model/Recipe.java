package com.meal.recs.model;

import com.meal.recs.data.entity.IngredientEntity;
import com.meal.recs.data.entity.RecipeEntity;
import com.meal.recs.utility.Utilities;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gardiary on 25/01/19.
 */
@Data
@ToString
@NoArgsConstructor
public class Recipe {
  private Long id;
  private String extId;
  private String name;
  private String image;
  private String recommendationMessage;
  private String description;
  private Time prepTime;
  private Time cookTime;
  private Integer servings;
  private Map<Long, Ingredient> ingredientsMap = new HashMap<>();
  private List<Ingredient> ingredients = new ArrayList<>();
  private List<String> directions;
  private List<Ingredient> neededIngredients = new ArrayList<>();
  private Source source;
  private String sourceUrl;
  private String keyword;

  public Recipe(Long id, String name, String image) {
    this.id = id;
    this.name = name;
    this.image = image;
  }

  public Recipe(Long id, String name, String image, String description, Time prepTime, Time cookTime, Integer servings) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.description = description;
    this.prepTime = prepTime;
    this.cookTime = cookTime;
    this.servings = servings;
  }

  public Recipe(RecipeEntity entity) {
    this.id = entity.getId();
    this.name = entity.getName();
    this.image = entity.getImage();
    this.description = entity.getDescription();
    this.prepTime = new Time(entity.getPrepTime(), entity.getPrepTimeUnit());
    this.cookTime = new Time(entity.getCookTime(), entity.getCookTimeUnit());
    this.servings = entity.getServings();
    this.keyword = entity.getKeyword();
    this.source = entity.getSource();
    this.sourceUrl = entity.getSourceUrl();
    this.extId = entity.getExtId();

    if(ObjectUtils.isNotEmpty(entity.getDirections())) {
      this.directions = Utilities.jsonStringToObj(entity.getDirections(), List.class);
    }

    for(IngredientEntity ingredientEntity : entity.getIngredients()) {
      Ingredient ingredient = new Ingredient(ingredientEntity);
      this.ingredients.add(ingredient);
      addIngredient(ingredient);
    }
  }
  /*public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getRecommendationMessage() {
    return recommendationMessage;
  }

  public void setRecommendationMessage(String recommendationMessage) {
    this.recommendationMessage = recommendationMessage;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Time getPrepTime() {
    return prepTime;
  }

  public void setPrepTime(Time prepTime) {
    this.prepTime = prepTime;
  }

  public Time getCookTime() {
    return cookTime;
  }

  public void setCookTime(Time cookTime) {
    this.cookTime = cookTime;
  }

  public Integer getServings() {
    return servings;
  }

  public void setServings(Integer servings) {
    this.servings = servings;
  }

  public Map<Long, Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(Map<Long, Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  public List<String> getDirections() {
    return directions;
  }

  public void setDirections(List<String> directions) {
    this.directions = directions;
  }

  public List<Ingredient> getNeededIngredients() {
    return neededIngredients;
  }

  public void setNeededIngredients(List<Ingredient> neededIngredients) {
    this.neededIngredients = neededIngredients;
  }*/

  public void addIngredient(Ingredient ingredient) {
    if(ingredientsMap == null) {
      ingredientsMap = new HashMap<>();
    }

    ingredientsMap.put(ingredient.getItem().getId(), ingredient);
  }

  public void addDirection(String direction) {
    if(directions == null) {
      directions = new ArrayList<>();
    }

    directions.add(direction);
  }

  public String getNeededIngredientsAsHtml() {
    if(neededIngredients == null || neededIngredients.size() == 0) {
      return "";
    }

    String html = "";
    int i = 0;
    for(Ingredient ingredient : neededIngredients) {
      html += "<span>" + ingredient.getItem().getName() + "</span>";
      i += 1;

      if(i < neededIngredients.size()) {
        html += "<br/>";
      }
    }

    return html;
  }

  public void resetIngredientsState() {
    for(Map.Entry<Long, Ingredient> entry : ingredientsMap.entrySet()) {
      Ingredient ingredient = entry.getValue();
      ingredient.setSelected(true);
    }
  }

  public void resetIngredientsState(boolean state) {
    for(Map.Entry<Long, Ingredient> entry : ingredientsMap.entrySet()) {
      Ingredient ingredient = entry.getValue();
      ingredient.setSelected(state);
    }
  }

  /*@Override
  public String toString() {
    return "Recipe[" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", picture='" + picture + '\'' +
            ", ingredients=" + ingredients +
            ", directions=" + directions +
            ']';
  }*/
}
