package edu.byui.shane.marykayinventoryapp;

import android.graphics.Bitmap;

/**
 * A container for important values needing to be sent to the View (MVC)
 */
public class ProductInfo {
    private String id, group, name, section, color;
    private float cost;
    private int numberInStock, numberOnOrder, highestNumberInInventory;
    private float inventoryValue, totalWorth;
    private Bitmap image;

    public ProductInfo(String id, String group, String name, String section, String color, float cost,
                       int numberInStock, int numberOnOrder, int highestNumberInInventory,
                       float inventoryValue, float totalWorth, Bitmap image) {
        this.id = id;
        this.group = group;
        this.name = name;
        this.section = section;
        this.color = color;
        this.cost = cost;
        this.numberInStock = numberInStock;
        this.numberOnOrder = numberOnOrder;
        this.highestNumberInInventory = highestNumberInInventory;
        this.inventoryValue = inventoryValue;
        this.totalWorth = totalWorth;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getSection() {
        return section;
    }

    public String getColor() {
        return color;
    }

    public float getCost() {
        return cost;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public int getNumberOnOrder() {
        return numberOnOrder;
    }

    public int getHighestNumberInInventory() {
        return highestNumberInInventory;
    }

    public float getInventoryValue() {
        return inventoryValue;
    }

    public float getTotalWorth() {
        return totalWorth;
    }

    public Bitmap getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "$" + getInventoryValue() + " of " + getColor() + " " + getName() + " " + getGroup();
    }
}
