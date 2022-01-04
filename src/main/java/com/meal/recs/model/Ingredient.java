package com.meal.recs.model;

import com.meal.recs.data.entity.IngredientEntity;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Created by gardiary on 25/01/19.
 */
@Data
@ToString
public class Ingredient {
  private Long id;
  private Long recipeId;
  private IngredientItem item;
  private Double amount;
  private IngredientUnit unit;
  private int packageCount;
  private String packageCountText;
  private String packageCountText2;
  private boolean selected = true;

  public Ingredient() {
  }

  public Ingredient(IngredientItem item, Double amount) {
    this.item = item;
    this.amount = amount;
  }

  public Ingredient(IngredientEntity entity) {
    this.id = entity.getId();
    this.recipeId = entity.getRecipeId();
    this.item = new IngredientItem(entity.getItem());
    this.amount = entity.getAmount();
    this.unit = entity.getUnit();
  }

  /*public IngredientItem getItem() {
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
  }*/

  public String getAmountAsString () {
    BigDecimal am = BigDecimal.valueOf(amount);

    return am.stripTrailingZeros().toPlainString();
  }

  /*public int getPackageCount() {
    return packageCount;
  }

  public void setPackageCount(int packageCount) {
    this.packageCount = packageCount;
  }

  public String getPackageCountText() {
    return packageCountText;
  }

  public void setPackageCountText(String packageCountText) {
    this.packageCountText = packageCountText;
  }

  public String getPackageCountText2() {
    return packageCountText2;
  }

  public void setPackageCountText2(String packageCountText2) {
    this.packageCountText2 = packageCountText2;
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
            ", packageCount=" + packageCount +
            ", selected=" + selected +
            ']';
  }*/
}
