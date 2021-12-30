package com.meal.recs.spoonacular;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * User: gardiary
 * Date: 28/12/21, 07.24
 */
@Data
@NoArgsConstructor
@ToString
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientItem {
    /*
    {
        "id": 23572,
        "name": "beef",
        "image": "beef-cubes-raw.png"
    }

    {
    "id": 10011549,
    "original": "spaghetti sauce",
    "originalName": "spaghetti sauce",
    "name": "spaghetti sauce",
    "possibleUnits": [
        "can",
        "g",
        "tin",
        "jar",
        "bottle",
        "oz",
        "cup",
        "serving",
        "tablespoon"
    ],
    "consistency": "solid",
    "aisle": "Pasta and Rice",
    "image": "tomato-sauce-or-pasta-sauce.jpg",
    "meta": [],
    "categoryPath": [
        "sauce"
    ]
}

    */
    private Long id;
    private String original;
    private String originalName;
    private String name;
    private String consistency;
    private String aisle;
    private String image;
    private String[] categoryPath;
    private String[] possibleUnits;
    private String unit;

    public IngredientItem(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
}
