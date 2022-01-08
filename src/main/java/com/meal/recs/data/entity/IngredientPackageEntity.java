package com.meal.recs.data.entity;

import com.meal.recs.model.IngredientItem;
import com.meal.recs.model.IngredientPackage;
import com.meal.recs.model.PackageUnit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * User: gardiary
 * Date: 27/12/21, 14.06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="mr_ingredient_package")
public class IngredientPackageEntity extends BaseEntity {

    public IngredientPackageEntity(IngredientPackage ingredientPackage) {
        this.unit = ingredientPackage.getUnit();
        this.item = new IngredientItemEntity(ingredientPackage.getItem().getId());
        this.itemAmount = ingredientPackage.getItemAmount();
    }

    @Column(name = "unit", length = 20)
    @Enumerated(value = EnumType.STRING)
    private PackageUnit unit;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private IngredientItemEntity item;

    @Column(name = "item_amount")
    private Double itemAmount;

    public String getItemAmountAsString() {
        BigDecimal am = BigDecimal.valueOf(itemAmount);
        return am.stripTrailingZeros().toPlainString();
    }
}
