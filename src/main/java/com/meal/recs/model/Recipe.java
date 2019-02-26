package com.meal.recs.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gardiary on 25/01/19.
 */
public class Recipe {
  private Long id;
  private String name;
  private String picture;
  private String recommendationMessage;
  private Map<Long, Ingredient> ingredients = new HashMap<>();
  private List<String> directions;
  private List<Ingredient> neededIngredients = new ArrayList<>();

  public Recipe() {
  }

  public Recipe(Long id, String name, String picture) {
    this.id = id;
    this.name = name;
    this.picture = picture;
  }

  public Long getId() {
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
  }

  public void addIngredient(Ingredient ingredient) {
    if(ingredients == null) {
      ingredients = new HashMap<>();
    }

    ingredients.put(ingredient.getItem().getId(), ingredient);
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
    for(Map.Entry<Long, Ingredient> entry : ingredients.entrySet()) {
      Ingredient ingredient = entry.getValue();
      ingredient.setSelected(true);
    }
  }

  @Override
  public String toString() {
    return "Recipe[" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", picture='" + picture + '\'' +
            ", ingredients=" + ingredients +
            ", directions=" + directions +
            ']';
  }
}
