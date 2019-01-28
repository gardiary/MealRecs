package com.meal.recs.model;

/**
 * Created by gardiary on 28/01/19.
 */
public class IngredientItem {
  private Long id;
  private String name;
  private String unit;

  public IngredientItem() {
  }

  public IngredientItem(Long id, String name, String unit) {
    this.id = id;
    this.name = name;
    this.unit = unit;
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

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  @Override
  public String toString() {
    return "IngredientItem[" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", unit='" + unit + '\'' +
            ']';
  }
}
