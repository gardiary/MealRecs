package com.meal.recs.model;

/**
 * Created by gardiary on 25/01/19.
 */
public class Ingredient {
  private Long id;
  private String name;
  private String amount;

  public Ingredient() {
  }

  public Ingredient(Long id, String name, String amount) {
    this.id = id;
    this.name = name;
    this.amount = amount;
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

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }
}
