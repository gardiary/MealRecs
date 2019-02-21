package com.meal.recs.model;

/**
 * Created by gardiary on 21/02/19.
 */
public class IngredientPackage {
  private Long id;
  private String unit;
  private IngredientItem item;
  private Double itemPackage;

  public IngredientPackage() {
  }

  public IngredientPackage(Long id, String unit, IngredientItem item, Double itemPackage) {
    this.id = id;
    this.unit = unit;
    this.item = item;
    this.itemPackage = itemPackage;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public IngredientItem getItem() {
    return item;
  }

  public void setItem(IngredientItem item) {
    this.item = item;
  }

  public Double getItemPackage() {
    return itemPackage;
  }

  public void setItemPackage(Double itemPackage) {
    this.itemPackage = itemPackage;
  }

  @Override
  public String toString() {
    return "IngredientPackage[" +
            "id=" + id +
            ", unit='" + unit + '\'' +
            ", item=" + item +
            ", itemPackage=" + itemPackage +
            ']';
  }
}
