package com.meal.recs.spoonacular;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * User: gardiary
 * Date: 01/01/22, 06.10
 */
@Data
@NoArgsConstructor
@ToString
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {
    /*
        {
            "id": 632179,
            "title": "Almond Sandwich Cookies",
            "image": "https://spoonacular.com/recipeImages/632179-312x231.jpg",
            "imageType": "jpg"
        }

        {
            "vegetarian": true,
            "vegan": false,
            "glutenFree": true,
            "dairyFree": true,
            "veryHealthy": false,
            "cheap": false,
            "veryPopular": false,
            "sustainable": false,
            "weightWatcherSmartPoints": 2,
            "gaps": "no",
            "lowFodmap": true,
            "aggregateLikes": 1,
            "spoonacularScore": 33.0,
            "healthScore": 4.0,
            "creditsText": "Foodista.com – The Cooking Encyclopedia Everyone Can Edit",
            "license": "CC BY 3.0",
            "sourceName": "Foodista",
            "pricePerServing": 33.33,
            "extendedIngredients": [
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
                },
                {
                    "id": 12061,
                    "aisle": "Nuts",
                    "image": "almonds.jpg",
                    "consistency": "solid",
                    "name": "almonds",
                    "nameClean": "almonds",
                    "original": "1/2 cup sliced almonds",
                    "originalString": "1/2 cup sliced almonds",
                    "originalName": "sliced almonds",
                    "amount": 0.5,
                    "unit": "cup",
                    "meta": [
                        "sliced"
                    ],
                    "metaInformation": [
                        "sliced"
                    ],
                    "measures": {
                        "us": {
                            "amount": 0.5,
                            "unitShort": "cups",
                            "unitLong": "cups"
                        },
                        "metric": {
                            "amount": 118.294,
                            "unitShort": "ml",
                            "unitLong": "milliliters"
                        }
                    }
                },
                {
                    "id": 2010,
                    "aisle": "Spices and Seasonings",
                    "image": "cinnamon.jpg",
                    "consistency": "solid",
                    "name": "cinnamon",
                    "nameClean": "cinnamon",
                    "original": "1 tablespoon cinnamon",
                    "originalString": "1 tablespoon cinnamon",
                    "originalName": "cinnamon",
                    "amount": 1.0,
                    "unit": "tablespoon",
                    "meta": [],
                    "metaInformation": [],
                    "measures": {
                        "us": {
                            "amount": 1.0,
                            "unitShort": "Tbsp",
                            "unitLong": "Tbsp"
                        },
                        "metric": {
                            "amount": 1.0,
                            "unitShort": "Tbsp",
                            "unitLong": "Tbsp"
                        }
                    }
                },
                {
                    "id": 1124,
                    "aisle": "Milk, Eggs, Other Dairy",
                    "image": "egg-white.jpg",
                    "consistency": "solid",
                    "name": "egg whites",
                    "nameClean": "egg whites",
                    "original": "1/4 cup egg whites (MEASURE!)",
                    "originalString": "1/4 cup egg whites (MEASURE!)",
                    "originalName": "egg whites (MEASURE!)",
                    "amount": 0.25,
                    "unit": "cup",
                    "meta": [
                        "(MEASURE!)"
                    ],
                    "metaInformation": [
                        "(MEASURE!)"
                    ],
                    "measures": {
                        "us": {
                            "amount": 0.25,
                            "unitShort": "cups",
                            "unitLong": "cups"
                        },
                        "metric": {
                            "amount": 59.147,
                            "unitShort": "ml",
                            "unitLong": "milliliters"
                        }
                    }
                },
                {
                    "id": 19335,
                    "aisle": "Baking",
                    "image": "sugar-in-bowl.png",
                    "consistency": "solid",
                    "name": "sugar",
                    "nameClean": "sugar",
                    "original": "1 tablespoon sugar",
                    "originalString": "1 tablespoon sugar",
                    "originalName": "sugar",
                    "amount": 1.0,
                    "unit": "tablespoon",
                    "meta": [],
                    "metaInformation": [],
                    "measures": {
                        "us": {
                            "amount": 1.0,
                            "unitShort": "Tbsp",
                            "unitLong": "Tbsp"
                        },
                        "metric": {
                            "amount": 1.0,
                            "unitShort": "Tbsp",
                            "unitLong": "Tbsp"
                        }
                    }
                }
            ],
            "id": 632179,
            "title": "Almond Sandwich Cookies",
            "readyInMinutes": 45,
            "servings": 20,
            "sourceUrl": "http://www.foodista.com/recipe/PZ7M84VR/almond-sandwich-cookies",
            "image": "https://spoonacular.com/recipeImages/632179-556x370.jpg",
            "imageType": "jpg",
            "summary": "Almond Sandwich Cookies might be just the dessert you are searching for. Watching your figure? This gluten free, dairy free, fodmap friendly, and vegetarian recipe has <b>71 calories</b>, <b>2g of protein</b>, and <b>5g of fat</b> per serving. For <b>33 cents per serving</b>, this recipe <b>covers 3%</b> of your daily requirements of vitamins and minerals. Not a lot of people made this recipe, and 1 would say it hit the spot. If you have sugar, almonds, cinnamon, and a few other ingredients on hand, you can make it. From preparation to the plate, this recipe takes roughly <b>45 minutes</b>. All things considered, we decided this recipe <b>deserves a spoonacular score of 35%</b>. This score is rather bad. Try <a href=\"https://spoonacular.com/recipes/almond-sandwich-cookies-552294\">Almond Sandwich Cookies</a>, <a href=\"https://spoonacular.com/recipes/almond-lace-sandwich-cookies-623049\">Almond Lace Sandwich Cookies</a>, and <a href=\"https://spoonacular.com/recipes/apricot-almond-sandwich-cookies-49176\">Apricot-almond Sandwich Cookies</a> for similar recipes.",
            "cuisines": [],
            "dishTypes": [
                "antipasti",
                "starter",
                "snack",
                "appetizer",
                "antipasto",
                "hor d'oeuvre"
            ],
            "diets": [
                "gluten free",
                "dairy free",
                "lacto ovo vegetarian",
                "fodmap friendly"
            ],
            "occasions": [],
            "winePairing": {},
            "instructions": "<ol><li>Preheat the oven to 350 degrees F. Line to large baking sheets with parchment paper.</li><li>Break the almond paste into chunks and place it in a food processor. Add the sugar and cinnamon and grind until fine.</li><li>Add the egg whites and pulse until smooth.</li><li>Scoop the mixture into a large zip bag and snip a hole in one corner.</li><li>Heres where the recipe variesIf the almond mixture in thick, pour the sliced almonds on to a plate. Then squeeze circle onto the almonds. Flip them over so the are covered with almonds on both sides, then lay them on the cookie sheets.</li><li>If the almond mixture is loose, place little mounds of sliced almonds on the cookies sheets where the cookies will be. Squeeze the mixture into circles on top of the little almond piles. Then sprinkle with a few more almonds.</li><li>Bake for 9-12 minutes. Keep an eye on them at the end because they turn dark quickly.</li><li>Allow the cookies to cool completely on the parchment paper. Then turn half of them over and top with jam or frosting. Give each cookie a top and sprinkle with powdered sugar.</li></ol>",
            "analyzedInstructions": [
                {
                    "name": "",
                    "steps": [
                        {
                            "number": 1,
                            "step": "Preheat the oven to 350 degrees F. Line to large baking sheets with parchment paper.Break the almond paste into chunks and place it in a food processor.",
                            "ingredients": [
                                {
                                    "id": 12071,
                                    "name": "almond paste",
                                    "localizedName": "almond paste",
                                    "image": "marzipan-or-almond-paste.jpg"
                                }
                            ],
                            "equipment": [
                                {
                                    "id": 404770,
                                    "name": "baking paper",
                                    "localizedName": "baking paper",
                                    "image": "baking-paper.jpg"
                                },
                                {
                                    "id": 404771,
                                    "name": "food processor",
                                    "localizedName": "food processor",
                                    "image": "food-processor.png"
                                },
                                {
                                    "id": 404727,
                                    "name": "baking sheet",
                                    "localizedName": "baking sheet",
                                    "image": "baking-sheet.jpg"
                                },
                                {
                                    "id": 404784,
                                    "name": "oven",
                                    "localizedName": "oven",
                                    "image": "oven.jpg",
                                    "temperature": {
                                        "number": 350.0,
                                        "unit": "Fahrenheit"
                                    }
                                }
                            ]
                        },
                        {
                            "number": 2,
                            "step": "Add the sugar and cinnamon and grind until fine.",
                            "ingredients": [
                                {
                                    "id": 2010,
                                    "name": "cinnamon",
                                    "localizedName": "cinnamon",
                                    "image": "cinnamon.jpg"
                                },
                                {
                                    "id": 19335,
                                    "name": "sugar",
                                    "localizedName": "sugar",
                                    "image": "sugar-in-bowl.png"
                                }
                            ],
                            "equipment": []
                        },
                        {
                            "number": 3,
                            "step": "Add the egg whites and pulse until smooth.Scoop the mixture into a large zip bag and snip a hole in one corner.Heres where the recipe varies",
                            "ingredients": [
                                {
                                    "id": 1124,
                                    "name": "egg whites",
                                    "localizedName": "egg whites",
                                    "image": "egg-white.jpg"
                                }
                            ],
                            "equipment": []
                        },
                        {
                            "number": 4,
                            "step": "If the almond mixture in thick, pour the sliced almonds on to a plate. Then squeeze circle onto the almonds. Flip them over so the are covered with almonds on both sides, then lay them on the cookie sheets.If the almond mixture is loose, place little mounds of sliced almonds on the cookies sheets where the cookies will be. Squeeze the mixture into circles on top of the little almond piles. Then sprinkle with a few more almonds.",
                            "ingredients": [
                                {
                                    "id": 10112061,
                                    "name": "sliced almonds",
                                    "localizedName": "sliced almonds",
                                    "image": "almonds.jpg"
                                },
                                {
                                    "id": 12061,
                                    "name": "almonds",
                                    "localizedName": "almonds",
                                    "image": "almonds.jpg"
                                },
                                {
                                    "id": 10118192,
                                    "name": "cookies",
                                    "localizedName": "cookies",
                                    "image": "shortbread-cookies.jpg"
                                },
                                {
                                    "id": 19142,
                                    "name": "mounds bar",
                                    "localizedName": "mounds bar",
                                    "image": "almond-joy.png"
                                }
                            ],
                            "equipment": [
                                {
                                    "id": 404727,
                                    "name": "baking sheet",
                                    "localizedName": "baking sheet",
                                    "image": "baking-sheet.jpg"
                                }
                            ]
                        },
                        {
                            "number": 5,
                            "step": "Bake for 9-12 minutes. Keep an eye on them at the end because they turn dark quickly.Allow the cookies to cool completely on the parchment paper. Then turn half of them over and top with jam or frosting. Give each cookie a top and sprinkle with powdered sugar.",
                            "ingredients": [
                                {
                                    "id": 19336,
                                    "name": "powdered sugar",
                                    "localizedName": "powdered sugar",
                                    "image": "powdered-sugar.jpg"
                                },
                                {
                                    "id": 19230,
                                    "name": "frosting",
                                    "localizedName": "frosting",
                                    "image": "vanilla-frosting.png"
                                },
                                {
                                    "id": 10118192,
                                    "name": "cookies",
                                    "localizedName": "cookies",
                                    "image": "shortbread-cookies.jpg"
                                },
                                {
                                    "id": 19297,
                                    "name": "jam",
                                    "localizedName": "jam",
                                    "image": "strawberry-jam.png"
                                }
                            ],
                            "equipment": [
                                {
                                    "id": 404770,
                                    "name": "baking paper",
                                    "localizedName": "baking paper",
                                    "image": "baking-paper.jpg"
                                },
                                {
                                    "id": 404784,
                                    "name": "oven",
                                    "localizedName": "oven",
                                    "image": "oven.jpg"
                                }
                            ],
                            "length": {
                                "number": 12,
                                "unit": "minutes"
                            }
                        }
                    ]
                }
            ],
            "originalId": null,
            "spoonacularSourceUrl": "https://spoonacular.com/almond-sandwich-cookies-632179"
        }

     */

    private Long id;
    @JsonProperty("title")
    private String name;
    private String description;
    private String image;
    @JsonProperty("extendedIngredients")
    private List<Ingredient> ingredients;
    @JsonProperty("analyzedInstructions")
    private List<RecipeInstruction> instructions;
    private List<RecipeInstructionStep> directions;
    private Integer preparationInMinutes;
    private Integer readyInMinutes;
    private Integer servings;
    @JsonProperty("spoonacularSourceUrl")
    private String sourceUrl;

    /*public List<RecipeInstructionStep> getDirections() {
        if(instructions == null || instructions.isEmpty()) {
            return null;
        }
        return instructions.get(0).getSteps();
    }*/
}
