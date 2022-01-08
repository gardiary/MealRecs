package com.meal.recs.model;

/**
 * User: gardiary
 * Date: 27/12/21, 12.46
 */
public enum PackageUnit {
    BOX("Box"), BAG("Bag"), LB("Lb"), JAR("Jar"), BOTTLE("Bottle"), CAN("Can"), HEAD("Head"), CARTON("Carton");

    String title;

    PackageUnit(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
