package com.meal.recs.model;

import java.math.BigDecimal;

/**
 * Created by gardiary on 25/01/19.
 */
public class Ingredient {
  private IngredientItem item;
  private Double amount;
  private boolean selected = true;

  public Ingredient() {
  }

  public Ingredient(IngredientItem item, Double amount) {
    this.item = item;
    this.amount = amount;
  }

  public IngredientItem getItem() {
    return item;
  }

  public void setItem(IngredientItem item) {
    this.item = item;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getAmountAsString () {
    BigDecimal am = BigDecimal.valueOf(amount);

    return am.stripTrailingZeros().toPlainString();
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  @Override
  public String toString() {
    return "Ingredient[" +
            "item=" + item +
            ", amount=" + amount +
            ", selected=" + selected +
            ']';
  }
}
