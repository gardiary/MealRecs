package com.meal.recs.model;

/**
 * User: gardiary
 * Date: 11/11/19, 11.12
 */
public class ShopItem {
    private String name;
    private Integer quantity;

    public ShopItem() {
    }

    public ShopItem(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
