package com.meal.recs.model;

/**
 * User: gardiary
 * Date: 27/12/21, 14.49
 */
public enum IngredientUnit {
    /*
    lbs
    oz
    oz sliced
    pcs
    
    "piece",
    "slice",
    "fruit",
    "g",
    "oz",
    "cup",
    "serving"
    "spoonful",
    "teaspoon",
    "cup",
    "serving",
    "tablespoon"
    "container",
    "ball",
    "package",
    "ounce",
    "chicken",
    "unit",
    "can",
    "pinch",
    "serving packet",
    "dash",
    "dozen"
    "tin"
    "tub",
    "carton",
    "square",
    "bag",
    "sheet",
    "nest",
    "clove",
    "head",
    "small",
    "strip",
    "large",
    "ring",
    "medium",
    "jar",
     */

    BAG, BALL, BOX, BUNCH, CAN, CARTON, CLOVE, CONTAINER, CUP, DASH, DOZEN, DROPS, FRUIT, G, HANDFUL, HEAD,
    INCH, JAR, LARGE, LEAVE, LB, MEDIUM, NEST, OZ, PACKAGE, PIECE, PINCH, PINT, RING, SERVING, SHEET, SLICE,
    SMALL, SPOONFUL, SPRIG, SQUARE, STICK, STRIP, TABLESPOON, TEASPOON, TIN, TUB, UNIT;

    public static IngredientUnit getUnit(String name) {
        if(name == null || name.isEmpty()) {
            return null;
        } else if(name.equalsIgnoreCase("cups") || name.equalsIgnoreCase("c")) {
            return CUP;
        } else if(name.equalsIgnoreCase("grams") || name.equalsIgnoreCase("gram")) {
            return G;
        } else if(name.equalsIgnoreCase("cloves")) {
            return CLOVE;
        } else if(name.equalsIgnoreCase("leaves")) {
            return LEAVE;
        } else if(name.equalsIgnoreCase("tablespoons") || name.equalsIgnoreCase("tbsps") ||
                name.equalsIgnoreCase("tbsp") || name.equalsIgnoreCase("tbs")) {
            return TABLESPOON;
        } else if(name.equalsIgnoreCase("teaspoons") || name.equalsIgnoreCase("tsps") || name.equalsIgnoreCase("tsp")) {
            return TEASPOON;
        } else if(name.equalsIgnoreCase("pound") || name.equalsIgnoreCase("pounds")) {
            return LB;
        } else if(name.equalsIgnoreCase("ounces") || name.equalsIgnoreCase("ounce")) {
            return OZ;
        } else if(name.equalsIgnoreCase("servings")) {
            return SERVING;
        } else if(name.equalsIgnoreCase("slices")) {
            return SLICE;
        } else if(name.equalsIgnoreCase("pkg")) {
            return PACKAGE;
        } else {
            try {
                return IngredientUnit.valueOf(name.toUpperCase());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
