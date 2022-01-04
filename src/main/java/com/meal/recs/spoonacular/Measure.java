package com.meal.recs.spoonacular;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * User: gardiary
 * Date: 01/01/22, 06.27
 */

@Data
@NoArgsConstructor
@ToString
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Measure {
    /*
        {
            "amount": 7.0,
            "unitShort": "oz",
            "unitLong": "ounces"
        }
    */

    private Double amount;
    private String unitShort;
    private String unitLong;
}
