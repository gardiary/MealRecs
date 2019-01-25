package com.meal.recs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gardiary on 25/01/19.
 */
public class Recipe {
  private Long id;
  private String name;
  private String picture;
  private List<Ingredient> ingredients;

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

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  public void addIngredient(Ingredient ingredient) {
    if(ingredients == null) {
      ingredients = new ArrayList<>();
    }

    ingredients.add(ingredient);
  }
}
