package edu.byui.shane.marykayinventoryapp;

/**
 * A container for important values needing to be sent to the View (MVC)
 */
public class ProductInfo {
    float totalCost;
    int amount;
    String name;

    public ProductInfo(float totalCost, int amount, String name) {
        this.totalCost = totalCost;
        this.amount = amount;
        this.name = name;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
