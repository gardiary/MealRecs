package com.meal.recs.model;

import com.meal.recs.data.entity.IngredientItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by gardiary on 28/01/19.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IngredientItem {
    private Long id;
    private String name;
    private IngredientUnit unit;
    private String image;
    private Source source;
    private String extId;

    public IngredientItem(Long id, String name, IngredientUnit unit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
    }

    public IngredientItem(IngredientItemEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.unit = entity.getUnit();
        this.image = entity.getImage();
        this.source = entity.getSource();
        this.extId = entity.getExtId();
    }

  /*public IngredientItem() {
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
  }*/
}
