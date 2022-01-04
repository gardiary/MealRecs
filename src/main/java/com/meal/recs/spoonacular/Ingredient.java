package com.meal.recs.spoonacular;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meal.recs.model.IngredientUnit;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;

/**
 * User: gardiary
 * Date: 01/01/22, 06.22
 */

@Data
@NoArgsConstructor
@ToString
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient {
    /*
        {
            "id": 12071,
            "aisle": "Baking",
            "image": "marzipan-or-almond-paste.jpg",
            "consistency": "solid",
            "name": "almond paste",
            "nameClean": "almond paste",
            "original": "7 ounces tube of almond paste",
            "originalString": "7 ounces tube of almond paste",
            "originalName": "almond paste",
            "amount": 7.0,
            "unit": "ounces",
            "meta": [],
            "metaInformation": [],
            "measures": {
                "us": {
                    "amount": 7.0,
                    "unitShort": "oz",
                    "unitLong": "ounces"
                },
                "metric": {
                    "amount": 198.447,
                    "unitShort": "g",
                    "unitLong": "grams"
                }
            }
        }
     */

    private Long id;
    private String name;
    @JsonProperty("original")
    private String nameLong;
    private String aisle;
    private String image;
    private Double amount;
    private String unit;
    private Map<String, Measure> measures;
    private boolean exists;
    private Long appId;

    public String getAmountAsString() {
        BigDecimal am = BigDecimal.valueOf(amount);
        return am.stripTrailingZeros().toPlainString();
    }

    public IngredientUnit getIngredientUnit() {
        return IngredientUnit.getUnit(this.unit);
    }
}
