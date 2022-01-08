package com.meal.recs.model;

import com.meal.recs.data.entity.IngredientPackageEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Created by gardiary on 21/02/19.
 */
@Data
@ToString
@NoArgsConstructor
public class IngredientPackage {
  private Long id;
  private PackageUnit unit;
  private IngredientItem item;
  private Double itemAmount;

  public IngredientPackage(Long id, PackageUnit unit, IngredientItem item, Double itemAmount) {
    this.id = id;
    this.unit = unit;
    this.item = item;
    this.itemAmount = itemAmount;
  }

  public IngredientPackage(IngredientPackageEntity entity) {
    this.id = entity.getId();
    this.unit = entity.getUnit();
    this.item = new IngredientItem(entity.getItem());
    this.itemAmount = entity.getItemAmount();
  }

  public String getItemAmountAsString() {
    BigDecimal am = BigDecimal.valueOf(itemAmount);
    return am.stripTrailingZeros().toPlainString();
  }

  /*public Long getId() {
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

  public Double getItemAmount() {
    return itemAmount;
  }

  public void setItemAmount(Double itemAmount) {
    this.itemAmount = itemAmount;
  }

  @Override
  public String toString() {
    return "IngredientPackage[" +
            "id=" + id +
            ", unit='" + unit + '\'' +
            ", item=" + item +
            ", itemAmount=" + itemAmount +
            ']';
  }*/
}
