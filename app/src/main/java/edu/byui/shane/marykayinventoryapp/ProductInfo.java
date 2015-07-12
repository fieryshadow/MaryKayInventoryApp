package edu.byui.shane.marykayinventoryapp;

import android.graphics.Bitmap;

/**
 * A container for important values needing to be sent to the View (MVC)
 */
public class ProductInfo {
    private String productNumber, category, name, color;
    private int section, numberInStock, numberOnOrder, highestNumberInInventory;
    private float cost, inventoryValue, totalWorth;
    private Bitmap image;

    public ProductInfo(String productNumber, String category, String name, int section, String color, float cost,
                       int numberInStock, int numberOnOrder, int highestNumberInInventory,
                       float inventoryValue, float totalWorth, Bitmap image) {
        this.productNumber = productNumber;
        this.category = category;
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

    public String getProductNumber() {
        return productNumber;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getSection() {
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
        return "$" + getInventoryValue() + " of " + getColor() + " " + getName() + " " + getCategory();
    }
}
