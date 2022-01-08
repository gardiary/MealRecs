package com.meal.recs.data.entity;

import com.meal.recs.model.IngredientUnit;
import com.meal.recs.model.Source;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * User: gardiary
 * Date: 27/12/21, 14.06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="mr_ingredient_item")
public class IngredientItemEntity extends BaseEntity {

    public IngredientItemEntity(Long id) {
        super.setId(id);
    }

    @Column(name = "name", length = 160, nullable = false)
    private String name;

    @Column(name = "unit", length = 20)
    @Enumerated(value = EnumType.STRING)
    private IngredientUnit unit;

    @Column(name = "image")
    private String image;

    @Column(name = "source", length = 20)
    @Enumerated(value = EnumType.STRING)
    private Source source;

    @Column(name = "ext_id", length = 32)
    private String extId;

    /*public IngredientItemEntity(IngredientItem model) {
        this.name = model.getName();
        //this.unit = model.getUnit();
    }*/
}
