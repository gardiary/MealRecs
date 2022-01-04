package com.meal.recs.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * User: gardiary
 * Date: 01/01/22, 07.03
 */
@Data
@NoArgsConstructor
@ToString
public class RecipeDirection {
    private Integer number;
    private String step;
}
